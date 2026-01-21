package de.eventmodelers.catalog.addmissingdata.internal

import de.eventmodelers.catalog.domain.commands.addmissingdata.AddDataFromBookSystemCommand
import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModelQuery
import de.eventmodelers.common.Processor
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.DisallowReplay
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.jvm.java

data class BookInfo(val title: String, val author: String, val description: String)

@Component
class FetchExternalDataFromWarehouse {

    fun fetch(id: String): BookInfo {
        return BookInfo("Harry Potter", "j.k. rowling", "description")
    }
}

/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115510
*/
@DisallowReplay
@Component
class ItemsFetcherProcessor(val adapter: FetchExternalDataFromWarehouse) : Processor {
    var logger = KotlinLogging.logger {}

    @Autowired
    lateinit var commandGateway: CommandGateway

    @EventHandler
    fun on(entryCreated: CatalogueEntryCreatedEvent) {
        // external systems call
        val bookDetails = adapter.fetch(entryCreated.itemId.toString())

        // fire command for each item
        commandGateway.send<AddDataFromBookSystemCommand>(
            AddDataFromBookSystemCommand(
                itemId = entryCreated.itemId.toString(),
                bookDetails.title, bookDetails.author,
                description = bookDetails.description,
                isbn = entryCreated.isbn
            )
        )
    }

    // runs every 60 seconds
    /*@Scheduled(fixedDelay = 60000)
    fun process() {

        // query all TODO Items
        queryGateway.query(
            CatalogEntryDetailsToFetchReadModelQuery(),
            CatalogEntryDetailsToFetchReadModel::class.java
        ).thenAccept {
            // iterate over all items
            it.data.forEach {


                // external systems call
                val bookDetails = adapter.fetch(it.itemId.toString())

                // fire command for each item
                commandGateway.send<AddDataFromBookSystemCommand>(
                    AddDataFromBookSystemCommand(
                        itemId = it.itemId.toString(),
                        bookDetails.title, bookDetails.author,
                        description = bookDetails.description,
                        isbn = it.isbn ?: ""
                    )
                )

                // if the command is eventually processed, this will close the TODO Item
            }

        }

    }*/
}

