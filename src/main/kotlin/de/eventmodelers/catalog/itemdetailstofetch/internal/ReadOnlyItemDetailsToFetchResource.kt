package de.eventmodelers.catalog.itemdetailstofetch.internal

import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872567
*/
@RestController
class ItemdetailstofetchResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/itemdetailstofetch")
  fun findReadModel(): CompletableFuture<ItemDetailsToFetchReadModel> {
    return queryGateway.query(
        ItemDetailsToFetchReadModelQuery(), ItemDetailsToFetchReadModel::class.java)
  }
}
