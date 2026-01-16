package de.eventmodelers.catalog.itemdetailsforpublication.internal

import de.eventmodelers.catalog.itemdetailsforpublication.ItemDetailsForPublicationReadModel
import de.eventmodelers.catalog.itemdetailsforpublication.ItemDetailsForPublicationReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655738119679
*/
@RestController
class ItemdetailsforpublicationResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/itemdetailsforpublication/{id}")
  fun findReadModel(
      @PathVariable("id") itemId: String
  ): CompletableFuture<ItemDetailsForPublicationReadModel> {
    return queryGateway.query(
        ItemDetailsForPublicationReadModelQuery(itemId),
        ItemDetailsForPublicationReadModel::class.java)
  }
}
