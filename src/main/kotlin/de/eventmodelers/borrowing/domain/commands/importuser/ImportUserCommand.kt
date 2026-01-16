package de.eventmodelers.borrowing.domain.commands.importuser

import de.eventmodelers.common.Command
import java.util.UUID
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711665
*/
data class ImportUserCommand(
    @TargetAggregateIdentifier var userId: UUID,
    var email: String,
    var name: String
) : Command
