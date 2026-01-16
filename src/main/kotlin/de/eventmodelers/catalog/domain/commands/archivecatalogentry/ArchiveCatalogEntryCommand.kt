package de.eventmodelers.catalog.domain.commands.archivecatalogentry

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655717997216
*/
data class ArchiveCatalogEntryCommand(@TargetAggregateIdentifier var itemId: String) : Command
