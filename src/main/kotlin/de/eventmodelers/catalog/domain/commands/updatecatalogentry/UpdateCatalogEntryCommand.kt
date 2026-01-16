package de.eventmodelers.catalog.domain.commands.updatecatalogentry

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655717997160
*/
data class UpdateCatalogEntryCommand(
    var author: String,
    var description: String,
    @TargetAggregateIdentifier var itemId: String,
    var title: String
) : Command
