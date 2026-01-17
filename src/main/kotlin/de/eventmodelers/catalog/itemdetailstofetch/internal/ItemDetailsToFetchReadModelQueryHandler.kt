package de.eventmodelers.catalog.itemdetailstofetch.internal

import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872567
*/
@Component
class ItemDetailsToFetchReadModelQueryHandler(
    private val repository: ItemDetailsToFetchReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: ItemDetailsToFetchReadModelQuery): ItemDetailsToFetchReadModel? {
    return ItemDetailsToFetchReadModel(repository.findAll())
  }
}
