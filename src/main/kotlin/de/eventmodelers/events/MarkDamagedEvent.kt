package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711631
*/
data class MarkDamagedEvent(var bookId: String, var reservationId: String, var userId: String) :
    Event
