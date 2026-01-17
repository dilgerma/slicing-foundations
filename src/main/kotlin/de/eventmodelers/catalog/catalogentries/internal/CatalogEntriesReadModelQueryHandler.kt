package de.eventmodelers.catalog.catalogentries.internal

import de.eventmodelers.catalog.catalogentries.CatalogEntriesReadModel
import de.eventmodelers.common.Query
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

class CatalogEntriesReadModelQuery : Query

@Component
class CatalogEntriesReadModelQueryHandler(
    private val repository: CatalogEntriesReadModelRepository
) {
  @QueryHandler
  fun handleQuery(query: CatalogEntriesReadModelQuery): CatalogEntriesReadModel {
    return CatalogEntriesReadModel(repository.findAll())
  }
}
