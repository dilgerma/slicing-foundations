package de.eventmodelers.catalog.domain.commands.addmissingdata

import org.axonframework.modelling.command.TargetAggregateIdentifier
import de.eventmodelers.common.Command


/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115512
*/
data class AddDataFromBookSystemCommand(
    @TargetAggregateIdentifier var itemId:String,
	var title:String,
	var author:String,
	var description:String,
	var isbn:String
): Command
