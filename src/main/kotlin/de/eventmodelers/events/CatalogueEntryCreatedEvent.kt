package de.eventmodelers.events

import de.eventmodelers.common.Event
import java.time.LocalDateTime

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655552383979
*/
data class CatalogueEntryCreatedEvent(
    var author: String,
    var description: String,
    var itemId: String,
    var title: String,
    var createdDate: LocalDateTime
) : Event
