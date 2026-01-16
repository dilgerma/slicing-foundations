package de.eventmodelers.catalog.itemstopublish.internal

import de.eventmodelers.catalog.itemstopublish.ItemsToPublishReadModelEntity
import de.eventmodelers.events.CatalogueEntryArchivedEvent
import de.eventmodelers.events.ItemPublishedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ItemsToPublishReadModelRepository : JpaRepository<ItemsToPublishReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655736566009
*/
@Component
class ItemsToPublishReadModelProjector(var repository: ItemsToPublishReadModelRepository) {

  @EventHandler
  fun on(event: CatalogueEntryArchivedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.itemId).orElse(ItemsToPublishReadModelEntity())
    entity.apply { itemId = event.itemId }.also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: ItemPublishedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.itemId).orElse(ItemsToPublishReadModelEntity())
    entity.apply { itemId = event.itemId }.also { this.repository.save(it) }
  }
}
