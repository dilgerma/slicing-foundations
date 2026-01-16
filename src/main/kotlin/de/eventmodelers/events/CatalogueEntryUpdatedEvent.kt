package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655552383980
*/
data class CatalogueEntryUpdatedEvent(
    var author: String,
    var description: String,
    var itemId: String,
    var title: String
) : Event
