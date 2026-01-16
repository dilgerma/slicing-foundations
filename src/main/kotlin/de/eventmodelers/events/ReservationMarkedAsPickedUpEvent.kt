package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711646
*/
data class ReservationMarkedAsPickedUpEvent(var reservationId: String, var userId: String) : Event
