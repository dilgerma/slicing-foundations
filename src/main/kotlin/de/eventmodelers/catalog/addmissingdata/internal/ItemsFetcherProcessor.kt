package de.eventmodelers.catalog.addmissingdata.internal

import de.eventmodelers.catalog.addmissingdata.internal.adapter.FetchExternalDataFromWarehouse
import de.eventmodelers.catalog.domain.commands.addmissingdata.AddMissingDataCommand
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModelQuery
import de.eventmodelers.common.Processor
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872707
*/
@Component
class ItemsFetcherProcessor(val fetchExternalDataFromWarehouse: FetchExternalDataFromWarehouse) :
    Processor {
  var logger = KotlinLogging.logger {}

  @Autowired lateinit var commandGateway: CommandGateway

  @Autowired lateinit var queryGateway: QueryGateway

  // runs every 60 seconds
  @Scheduled(fixedDelay = 10000)
  fun process() {

    // query all TODO Items
    queryGateway
        .query(ItemDetailsToFetchReadModelQuery(), ItemDetailsToFetchReadModel::class.java)
        .thenAccept {
          // iterate over all items
          it.data.forEach {
            // external systems call
            val bookDetails = fetchExternalDataFromWarehouse.fetch(it.itemId)

            // fire command for each item
            commandGateway.send<AddMissingDataCommand>(
                AddMissingDataCommand(
                    itemId = it.itemId,
                    bookDetails.title,
                    bookDetails.author,
                    bookDetails.description))
          }
        }
  }
}
