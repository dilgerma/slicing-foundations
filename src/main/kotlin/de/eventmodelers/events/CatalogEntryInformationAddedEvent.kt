package de.eventmodelers.events

import de.eventmodelers.common.Event



/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115497
*/
data class CatalogEntryInformationAddedEvent(
    var itemId:String,
	var title:String,
	var author:String,
	var description:String,
	var isbn:String
) : Event
