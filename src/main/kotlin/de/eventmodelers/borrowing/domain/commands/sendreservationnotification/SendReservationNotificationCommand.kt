package de.eventmodelers.borrowing.domain.commands.sendreservationnotification

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711641
*/
data class SendReservationNotificationCommand(
    var email: String,
    var name: String,
    var userId: String,
    @TargetAggregateIdentifier var reservationId: String
) : Command
