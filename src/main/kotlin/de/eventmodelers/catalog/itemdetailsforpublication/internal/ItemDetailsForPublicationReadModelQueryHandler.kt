package de.eventmodelers.catalog.itemdetailsforpublication.internal

import de.eventmodelers.catalog.itemdetailsforpublication.ItemDetailsForPublicationReadModel
import de.eventmodelers.catalog.itemdetailsforpublication.ItemDetailsForPublicationReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655738119679
*/
@Component
class ItemDetailsForPublicationReadModelQueryHandler(
    private val repository: ItemDetailsForPublicationReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: ItemDetailsForPublicationReadModelQuery
  ): ItemDetailsForPublicationReadModel? {

    if (!repository.existsById(query.itemId)) {
      return null
    }
    return ItemDetailsForPublicationReadModel(repository.findById(query.itemId).get())
  }
}
