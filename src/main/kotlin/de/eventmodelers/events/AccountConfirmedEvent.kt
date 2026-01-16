package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392004611
*/
data class AccountConfirmedEvent(var userId: String, var email: String) : Event
