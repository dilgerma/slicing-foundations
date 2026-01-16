package de.eventmodelers.catalog.catalogentrydetails.internal

import de.eventmodelers.catalog.catalogentrydetails.CatalogEntryDetailsReadModel
import de.eventmodelers.catalog.catalogentrydetails.CatalogEntryDetailsReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655723786459
*/
@Component
class CatalogEntryDetailsReadModelQueryHandler(
    private val repository: CatalogEntryDetailsReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: CatalogEntryDetailsReadModelQuery): CatalogEntryDetailsReadModel? {

    if (!repository.existsById(query.itemId)) {
      return null
    }
    return CatalogEntryDetailsReadModel(repository.findById(query.itemId).get())
  }
}
