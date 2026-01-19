package de.eventmodelers.registration.domain.commands.confirmaccount

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392004706
*/
data class ConfirmAccountCommand(
    var email: String,
    @TargetAggregateIdentifier var user_id: String,
    var token: String
) : Command
