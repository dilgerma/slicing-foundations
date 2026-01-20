package de.eventmodelers.domain.createcatalogentry

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateCatalogEntryCommand(
    @TargetAggregateIdentifier
    val itemId: String,
    val title:String,
    val author:String,
    val description:String,
    val isbn:String
): Command