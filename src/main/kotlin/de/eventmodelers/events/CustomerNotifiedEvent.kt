package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391355641
*/
data class CustomerNotifiedEvent(
    var userId: String,
    var email: String,
    var name: String,
    var token: String
) : Event
