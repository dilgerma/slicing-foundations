package de.eventmodelers.catalog.catalogentries.internal

import de.eventmodelers.catalog.ProcessingGroups
import de.eventmodelers.catalog.catalogentries.CatalogEntriesReadModelEntity
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.eventhandling.ResetHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface CatalogEntriesReadModelRepository : JpaRepository<CatalogEntriesReadModelEntity, String>

@ProcessingGroup(ProcessingGroups.CATALOG)
@Component
class CatalogEntriesReadModelProjector(private val repository: CatalogEntriesReadModelRepository) {

  @EventHandler
  fun on(event: CatalogueEntryCreatedEvent) {
      //wait for 30 seconds
      Thread.sleep(5000)
    val entity = repository.findById(event.itemId).orElse(CatalogEntriesReadModelEntity())
    entity
        .apply {
          itemId = event.itemId
          title = event.title
        }
        .also { repository.save(it) }
  }

    @ResetHandler
    fun onReset() {
        repository.deleteAll()
    }
}
