package de.eventmodelers.events

import de.eventmodelers.common.Event

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655390014478
*/
data class AccountCreatedEvent(var userId: String, var email: String, var name: String) : Event
