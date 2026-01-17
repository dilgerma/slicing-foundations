package de.eventmodelers.catalog.catalogentries.internal

import de.eventmodelers.catalog.catalogentries.CatalogEntriesReadModel
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655718555043
*/
@RestController
class CatalogentriesResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/catalogentries")
  fun findReadModel(): CompletableFuture<CatalogEntriesReadModel> {
    return queryGateway.query(CatalogEntriesReadModelQuery(), CatalogEntriesReadModel::class.java)
  }
}
