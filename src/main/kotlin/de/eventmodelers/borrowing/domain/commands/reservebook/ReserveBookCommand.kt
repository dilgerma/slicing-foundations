package de.eventmodelers.borrowing.domain.commands.reservebook

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711634
*/
data class ReserveBookCommand(
    var bookId: String,
    var userId: String,
    @TargetAggregateIdentifier var reservationId: String
) : Command
