package de.eventmodelers.catalog.catalogentrydetails.internal

import de.eventmodelers.catalog.catalogentrydetails.CatalogEntryDetailsReadModelEntity
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import de.eventmodelers.events.CatalogueEntryUpdatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface CatalogEntryDetailsReadModelRepository :
    JpaRepository<CatalogEntryDetailsReadModelEntity, String>

/*

# Spec Start
Title: spec: Catalog Entry Details - scenario
### Given (Events):
  * 'Catalogue entry updated' (SPEC_EVENT)
Fields:
 - author:
 - description:
 - itemId: 1
 - title: Harry Potter
  * 'Catalogue entry created' (SPEC_EVENT)
Fields:
 - author:
 - description:
 - itemId: 1
 - title: Harry Pitter
### When (Command): None
### Then:
  * 'Catalog Entry Details' (SPEC_READMODEL)
Fields:
 - author:
 - description:
 - itemId:
 - title: Harry Potter
  * 'Catalog Entry Details' (SPEC_READMODEL)
Fields:
 - author:
 - description:
 - itemId:
 - title: Harry Pitter
# Spec End */
/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655723786459
*/
@Component
class CatalogEntryDetailsReadModelProjector(
    var repository: CatalogEntryDetailsReadModelRepository
) {

  @EventHandler
  fun on(event: CatalogueEntryCreatedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.itemId).orElse(CatalogEntryDetailsReadModelEntity())
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
  fun on(event: CatalogueEntryUpdatedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.itemId).orElse(CatalogEntryDetailsReadModelEntity())
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
