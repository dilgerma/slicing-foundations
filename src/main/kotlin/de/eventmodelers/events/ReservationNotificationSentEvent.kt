package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711628
*/
data class ReservationNotificationSentEvent(
    var email: String,
    var name: String,
    var userId: String,
    var reservationId: String
) : Event
