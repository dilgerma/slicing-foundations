package de.eventmodelers.borrowing.domain.commands.markdamaged

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711656
*/
data class MarkDamagedCommand(
    var bookId: String,
    @TargetAggregateIdentifier var reservationId: String,
    var userId: String
) : Command
