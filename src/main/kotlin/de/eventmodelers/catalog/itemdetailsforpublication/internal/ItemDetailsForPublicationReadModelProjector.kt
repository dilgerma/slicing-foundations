package de.eventmodelers.catalog.itemdetailsforpublication.internal

import de.eventmodelers.catalog.itemdetailsforpublication.ItemDetailsForPublicationReadModelEntity
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import de.eventmodelers.events.CatalogueEntryUpdatedEvent
import de.eventmodelers.events.ItemPublishedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ItemDetailsForPublicationReadModelRepository :
    JpaRepository<ItemDetailsForPublicationReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655738119679
*/
@Component
class ItemDetailsForPublicationReadModelProjector(
    var repository: ItemDetailsForPublicationReadModelRepository
) {

  @EventHandler
  fun on(event: CatalogueEntryCreatedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.itemId).orElse(ItemDetailsForPublicationReadModelEntity())
    entity
        .apply {
          author = event.author
          description = event.description
          itemId = event.itemId
          title = event.title
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: ItemPublishedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.itemId).orElse(ItemDetailsForPublicationReadModelEntity())
    entity.apply { itemId = event.itemId }.also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: CatalogueEntryUpdatedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.itemId).orElse(ItemDetailsForPublicationReadModelEntity())
    entity
        .apply {
          author = event.author
          description = event.description
          itemId = event.itemId
          title = event.title
        }
        .also { this.repository.save(it) }
  }
}
