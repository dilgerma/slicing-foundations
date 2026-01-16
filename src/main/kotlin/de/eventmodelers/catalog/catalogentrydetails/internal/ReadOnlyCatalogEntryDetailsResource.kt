package de.eventmodelers.catalog.catalogentrydetails.internal

import de.eventmodelers.catalog.catalogentrydetails.CatalogEntryDetailsReadModel
import de.eventmodelers.catalog.catalogentrydetails.CatalogEntryDetailsReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655723786459
*/
@RestController
class CatalogentrydetailsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/catalogentrydetails/{id}")
  fun findReadModel(
      @PathVariable("id") itemId: String
  ): CompletableFuture<CatalogEntryDetailsReadModel> {
    return queryGateway.query(
        CatalogEntryDetailsReadModelQuery(itemId), CatalogEntryDetailsReadModel::class.java)
  }
}
