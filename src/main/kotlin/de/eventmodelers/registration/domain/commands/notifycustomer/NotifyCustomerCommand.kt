package de.eventmodelers.registration.domain.commands.notifycustomer

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010383
*/
data class NotifyCustomerCommand(
    var email: String,
    var name: String,
    @TargetAggregateIdentifier var userId: String,
    var token: String
) : Command
