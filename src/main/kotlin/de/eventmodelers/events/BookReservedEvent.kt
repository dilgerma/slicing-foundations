package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711627
*/
data class BookReservedEvent(var userId: String, var bookId: String, var reservationId: String) :
    Event
