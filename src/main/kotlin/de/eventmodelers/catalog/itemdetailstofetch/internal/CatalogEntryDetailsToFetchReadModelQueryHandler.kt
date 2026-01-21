package de.eventmodelers.catalog.itemdetailstofetch.internal

import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModel
import org.springframework.stereotype.Component
import de.eventmodelers.catalog.itemdetailstofetch.internal.CatalogEntryDetailsToFetchReadModelRepository
import org.axonframework.queryhandling.QueryHandler
import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModelQuery


/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115509
*/
@Component
class CatalogEntryDetailsToFetchReadModelQueryHandler(private val repository:CatalogEntryDetailsToFetchReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: CatalogEntryDetailsToFetchReadModelQuery): CatalogEntryDetailsToFetchReadModel? {
      return CatalogEntryDetailsToFetchReadModel(repository.findAll())
  }

}
