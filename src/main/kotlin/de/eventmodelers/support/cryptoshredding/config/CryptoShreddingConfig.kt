package de.eventmodelers.support.cryptoshredding.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.eventmodelers.support.cryptoshredding.CryptoShreddingSerializer
import de.eventmodelers.support.cryptoshredding.KeyManager
import de.eventmodelers.support.cryptoshredding.encryption.Decrypter
import de.eventmodelers.support.cryptoshredding.encryption.Encrypter
import org.axonframework.serialization.ChainingConverter
import org.axonframework.serialization.RevisionResolver
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.beans.factory.BeanClassLoaderAware
import org.springframework.beans.factory.BeanFactoryUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class CryptoShreddingConfig : BeanClassLoaderAware {
    private lateinit var classLoader: ClassLoader

    @Autowired
    private lateinit var encrypter: Encrypter

    @Autowired
    private lateinit var decrypter: Decrypter

    @Primary
    @Bean
    fun cryptoShreddingSerializer(
        applicationContext: ApplicationContext,
        revisionResolver: RevisionResolver,
        keyManager: KeyManager,
    ): CryptoShreddingSerializer {
        val objectMapperBeans =
            BeanFactoryUtils.beansOfTypeIncludingAncestors(
                applicationContext,
                ObjectMapper::class.java,
            )
        val objectMapper =
            if (objectMapperBeans.containsKey("defaultAxonObjectMapper")) {
                objectMapperBeans["defaultAxonObjectMapper"]
            } else {
                jacksonObjectMapper()
            }
        val converter = ChainingConverter(classLoader)
        val builder =
            JacksonSerializer
                .builder()
                .revisionResolver(revisionResolver)
                .converter(converter)
                .objectMapper(objectMapper)
        return CryptoShreddingSerializer(keyManager, encrypter, decrypter, builder)
    }

    override fun setBeanClassLoader(classLoader: ClassLoader) {
        this.classLoader = classLoader
    }
}
