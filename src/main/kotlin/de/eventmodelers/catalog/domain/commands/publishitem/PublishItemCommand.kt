package de.eventmodelers.catalog.domain.commands.publishitem

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655732594496
*/
data class PublishItemCommand(@TargetAggregateIdentifier var itemId: String) : Command
