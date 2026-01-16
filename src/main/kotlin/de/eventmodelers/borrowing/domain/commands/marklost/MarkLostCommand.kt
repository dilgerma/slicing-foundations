package de.eventmodelers.borrowing.domain.commands.marklost

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711653
*/
data class MarkLostCommand(
    var userId: String,
    var bookId: String,
    @TargetAggregateIdentifier var reservationId: String
) : Command
