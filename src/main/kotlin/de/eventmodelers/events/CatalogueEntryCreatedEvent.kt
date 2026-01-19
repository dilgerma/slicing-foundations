package de.eventmodelers.events

import de.eventmodelers.common.Event
import de.eventmodelers.support.cryptoshredding.EncryptedField
import de.eventmodelers.support.cryptoshredding.EncryptionKeyIdentifier
import java.time.LocalDateTime

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655552383979
*/
data class CatalogueEntryCreatedEvent(
    @EncryptedField
    var author: String,
    var description: String,
    @EncryptionKeyIdentifier
    var itemId: String,
    var title: String,
    var createdDate: LocalDateTime
) : Event
