package de.eventmodelers.support.cryptoshredding

import com.fasterxml.jackson.core.type.TypeReference
import de.eventmodelers.support.cryptoshredding.encryption.Decrypter
import de.eventmodelers.support.cryptoshredding.encryption.Encrypter
import de.eventmodelers.support.cryptoshredding.persistence.CryptoKeyRepository
import de.eventmodelers.support.cryptoshredding.persistence.PersistedSecretKey
import org.axonframework.serialization.SerializedObject
import org.axonframework.serialization.SerializedType
import org.axonframework.serialization.SimpleSerializedObject
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.Base64
import java.util.concurrent.ConcurrentHashMap
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Target(AnnotationTarget.FIELD)
annotation class EncryptedField

@Target(AnnotationTarget.FIELD)
annotation class EncryptionKeyIdentifier

@Component
class KeyManager(
    val repository: CryptoKeyRepository,
) {
    private val keyCache = ConcurrentHashMap<String, SecretKey>()

    fun getKey(identifier: String): SecretKey? {
        return keyCache[identifier] ?: repository.findByIdOrNull(identifier)
            ?.let { SecretKeySpec(it.key, "AES") }
            ?.also { keyCache[identifier] = it }
    }

    fun getOrCreateKey(identifier: String): SecretKey {

        val existingKey = getKey(identifier)
        if (existingKey != null) {
            return existingKey
        } else {
            val genKey = KeyGenerator.getInstance("AES").apply { init(256) }.generateKey()
            val persistedKey = repository.save(
                PersistedSecretKey().apply {
                    this.key = genKey.encoded
                    this.id = identifier
                },
            )
            return  SecretKeySpec(persistedKey.key, "AES")
        }
    }
}

class CryptoShreddingSerializer(
    private val keyManager: KeyManager,
    private val encrypter: Encrypter,
    private val decrypter: Decrypter,
    jacksonBuilder: Builder,
) : JacksonSerializer(jacksonBuilder) {
    override fun <S, T> deserialize(serializedObject: SerializedObject<S>): T {
        val objectType = classForType(serializedObject.type)
        val fieldsToDecrypt = objectType.declaredFields.filter { it.isAnnotationPresent(EncryptedField::class.java) }
        if (fieldsToDecrypt.isEmpty()) {
            return super.deserialize(serializedObject)
        }

        val jsonMap =
            objectMapper.readValue(
                serializedObject.data as ByteArray,
                object : TypeReference<MutableMap<String, Any>>() {},
            )
        val keyIdentifier =
            jsonMap[getEncryptionKeyFieldName(objectType)]?.toString()
                ?: throw IllegalStateException("Missing encryption key identifier in serialized data")

        val secretKey = keyManager.getKey(keyIdentifier)

        fieldsToDecrypt.forEach { field ->
            val encryptedValue = jsonMap[field.name]?.toString() ?: return@forEach
            val decryptedBytes =
                secretKey?.let {
                    decrypter.decrypt(it, Base64.getDecoder().decode(encryptedValue))
                } ?: DELETED_DEFAULT
            jsonMap[field.name] = decryptedBytes
        }
        return objectMapper.convertValue(jsonMap, objectType) as T
    }

    override fun <T : Any?> serialize(
        `object`: Any?,
        expectedRepresentation: Class<T>,
    ): SerializedObject<T> {
        if (`object` == null) {
            throw IllegalStateException("cannot serialize null object")
        }
        val fieldsToEncrypt =
            `object`::class.java.declaredFields.filter { it.isAnnotationPresent(EncryptedField::class.java) }
        if (fieldsToEncrypt.isEmpty()) {
            return super.serialize(`object`, expectedRepresentation)
        }

        val keyIdentifier = getKeyIdentifier(`object`)
        val secretKey = keyManager.getOrCreateKey(keyIdentifier)

        val jsonMap =
            objectMapper.convertValue(`object`, object : TypeReference<MutableMap<String, Any>>() {}).apply {
                fieldsToEncrypt.forEach { field ->
                    field.isAccessible = true
                    val fieldValue = field.get(`object`)?.toString()
                    if (fieldValue != null) {
                        val encryptedValue = encrypter.encrypt(secretKey, fieldValue)
                        this[field.name] = Base64.getEncoder().encodeToString(encryptedValue)
                    }
                }
            }

        val serializedData = objectMapper.writeValueAsBytes(jsonMap)
        return SimpleSerializedObject(
            serializedData as T,
            expectedRepresentation,
            super.typeForClass(`object`::class.java),
        )
    }

    override fun <T> canSerializeTo(expectedRepresentation: Class<T>): Boolean = super.canSerializeTo(expectedRepresentation)

    override fun typeForClass(type: Class<*>?): SerializedType = super.typeForClass(type)

    private fun getKeyIdentifier(instance: Any): String =
        instance::class.java.declaredFields
            .firstOrNull {
                it.isAnnotationPresent(EncryptionKeyIdentifier::class.java)
            }?.apply { isAccessible = true }
            ?.get(instance)
            ?.toString()
            ?: throw IllegalStateException("Missing encryption key identifier")

    private fun getEncryptionKeyFieldName(type: Class<*>): String =
        type.declaredFields
            .firstOrNull {
                it.isAnnotationPresent(EncryptionKeyIdentifier::class.java)
            }?.name ?: throw IllegalStateException("No field annotated with @EncryptionKeyIdentifier")

    companion object {
        const val DELETED_DEFAULT = "deleted"
    }
}
