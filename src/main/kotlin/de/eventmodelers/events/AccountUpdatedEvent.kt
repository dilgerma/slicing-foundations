package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392353848
*/
data class AccountUpdatedEvent(var user_id: String, var email: String, var name: String) : Event
