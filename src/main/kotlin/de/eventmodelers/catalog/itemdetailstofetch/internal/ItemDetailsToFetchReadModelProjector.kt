package de.eventmodelers.catalog.itemdetailstofetch.internal

import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModelEntity
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import de.eventmodelers.events.ItemInformationAddedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ItemDetailsToFetchReadModelRepository :
    JpaRepository<ItemDetailsToFetchReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872567
*/
@Component
class ItemDetailsToFetchReadModelProjector(var repository: ItemDetailsToFetchReadModelRepository) {

  @EventHandler
  fun on(event: CatalogueEntryCreatedEvent) {
    val entity = this.repository.findById(event.itemId).orElse(ItemDetailsToFetchReadModelEntity())
    entity
        .apply {
          itemId = event.itemId
          fetched = false
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: ItemInformationAddedEvent) {
    this.repository.deleteById(event.itemId)
  }
}
