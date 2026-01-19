package de.eventmodelers.catalog.catalogentries.internal

import de.eventmodelers.catalog.catalogentries.CatalogEntriesReadModelEntity
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import de.eventmodelers.support.notifications.NotifyClient
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface CatalogEntriesReadModelRepository : JpaRepository<CatalogEntriesReadModelEntity, String>

/*

# Spec Start
Title: spec: Catalog Entries - scenario
### Given (Events):
  * 'Catalogue entry created' (SPEC_EVENT)
Fields:
 - author:
 - description:
 - itemId: 2
 - title: Lord of the Rings
  * 'Catalogue entry created' (SPEC_EVENT)
Fields:
 - author:
 - description:
 - itemId: 1
 - title: Harry Potter
### When (Command): None
### Then:
  * 'Catalog Entries' (SPEC_READMODEL)
Expected Items in List:

** Item 1
 - title: Harry Potter
 - itemId: 1

** Item 2
 - title: Lord of the Rings
 - itemId: 2
# Spec End */
/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655718555043
*/
@Component
class CatalogEntriesReadModelProjector(var repository: CatalogEntriesReadModelRepository) {

  @NotifyClient
  @EventHandler
  fun on(event: CatalogueEntryCreatedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.itemId).orElse(CatalogEntriesReadModelEntity())
    entity
        .apply {
          title = event.title
          itemId = event.itemId
        }
        .also { this.repository.save(it) }
  }
}
