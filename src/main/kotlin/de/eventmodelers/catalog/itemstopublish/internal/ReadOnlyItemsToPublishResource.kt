package de.eventmodelers.catalog.itemstopublish.internal

import de.eventmodelers.catalog.itemstopublish.ItemsToPublishReadModel
import de.eventmodelers.catalog.itemstopublish.ItemsToPublishReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655736566009
*/
@RestController
class ItemstopublishResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/itemstopublish/{id}")
  fun findReadModel(
      @PathVariable("id") itemId: String
  ): CompletableFuture<ItemsToPublishReadModel> {
    return queryGateway.query(
        ItemsToPublishReadModelQuery(itemId), ItemsToPublishReadModel::class.java)
  }
}
