package de.eventmodelers.catalog.domain.commands.createcatalogentry

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655717997017
*/
data class CreateCatalogEntryCommand(
    @TargetAggregateIdentifier var itemId: String,
    var title: String,
    var author: String,
    var description: String,
    var isbn: String
) : Command
