package de.eventmodelers.catalog.catalogentries.internal

import de.eventmodelers.catalog.catalogentries.CatalogEntriesReadModel
import de.eventmodelers.catalog.catalogentries.CatalogEntriesReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655718555043
*/
@Component
class CatalogEntriesReadModelQueryHandler(
    private val repository: CatalogEntriesReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: CatalogEntriesReadModelQuery): CatalogEntriesReadModel? {
    return CatalogEntriesReadModel(repository.findAll())
  }
}
