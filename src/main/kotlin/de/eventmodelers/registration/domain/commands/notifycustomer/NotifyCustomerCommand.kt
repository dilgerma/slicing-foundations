package de.eventmodelers.registration.domain.commands.notifycustomer

import de.eventmodelers.common.Command

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010383
*/
data class NotifyCustomerCommand(
    var email: String,
    var name: String,
    var userId: String,
    var token: String
) : Command
