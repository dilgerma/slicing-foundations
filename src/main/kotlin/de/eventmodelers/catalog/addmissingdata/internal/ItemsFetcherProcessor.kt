package de.eventmodelers.catalog.addmissingdata.internal

import de.eventmodelers.catalog.addmissingdata.internal.adapter.FetchExternalDataFromWarehouse
import de.eventmodelers.catalog.domain.commands.addmissingdata.AddMissingDataCommand
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModelQuery
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

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872707
*/
@DisallowReplay
@Component
class ItemsFetcherProcessor(val fetchExternalDataFromWarehouse: FetchExternalDataFromWarehouse) :
    Processor {
    var logger = KotlinLogging.logger {}

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    // runs every 60 seconds
    @EventHandler
    fun on(event: CatalogueEntryCreatedEvent) {

        // external systems call
        val bookDetails = fetchExternalDataFromWarehouse.fetch(event.itemId)

        // fire command
        commandGateway.send<AddMissingDataCommand>(
            AddMissingDataCommand(
                itemId = event.itemId,
                bookDetails.title,
                bookDetails.author,
                bookDetails.description
            )
        )
    }
}
