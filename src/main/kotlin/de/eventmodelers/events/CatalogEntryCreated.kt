package de.eventmodelers.events

import de.eventmodelers.common.Event

data class CatalogEntryCreatedEvent(
    val itemId: String,
    val title:String,
    val author:String,
    val description:String,
    val isbn:String
): Event