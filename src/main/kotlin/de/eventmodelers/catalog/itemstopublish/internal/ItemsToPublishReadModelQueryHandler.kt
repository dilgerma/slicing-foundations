package de.eventmodelers.catalog.itemstopublish.internal

import de.eventmodelers.catalog.itemstopublish.ItemsToPublishReadModel
import de.eventmodelers.catalog.itemstopublish.ItemsToPublishReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655736566009
*/
@Component
class ItemsToPublishReadModelQueryHandler(
    private val repository: ItemsToPublishReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: ItemsToPublishReadModelQuery): ItemsToPublishReadModel? {

    if (!repository.existsById(query.itemId)) {
      return null
    }
    return ItemsToPublishReadModel(repository.findById(query.itemId).get())
  }
}
