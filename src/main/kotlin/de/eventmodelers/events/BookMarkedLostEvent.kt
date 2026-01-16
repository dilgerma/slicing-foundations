package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711630
*/
data class BookMarkedLostEvent(var bookId: String, var reservationId: String, var userId: String) :
    Event
