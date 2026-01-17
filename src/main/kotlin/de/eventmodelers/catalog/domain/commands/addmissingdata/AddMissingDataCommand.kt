package de.eventmodelers.catalog.domain.commands.addmissingdata

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872720
*/
data class AddMissingDataCommand(
    @TargetAggregateIdentifier var itemId: String,
    var title: String?,
    var author: String?,
    var description: String?
) : Command
