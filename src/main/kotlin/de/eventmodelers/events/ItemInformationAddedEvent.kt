package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872816
*/
data class ItemInformationAddedEvent(
    var itemId: String,
    var title: String?,
    var author: String?,
    var description: String?
) : Event
