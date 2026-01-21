package de.eventmodelers.catalog.itemdetailstofetch.internal

import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModelQuery
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import java.util.concurrent.CompletableFuture


/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115509
*/
@RestController
class ItemdetailstofetchResource(
    private var queryGateway: QueryGateway
) {

    var logger = KotlinLogging.logger {}

    @CrossOrigin
    @GetMapping("/itemdetailstofetch")
    fun findReadModel(): CompletableFuture<CatalogEntryDetailsToFetchReadModel> {
        return queryGateway.query(
            CatalogEntryDetailsToFetchReadModelQuery(),
            CatalogEntryDetailsToFetchReadModel::class.java
        )
    }

}
