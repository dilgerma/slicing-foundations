package de.eventmodelers.catalog.catalogentries.internal

import de.eventmodelers.events.CatalogEntryCreatedEvent
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Table(name = "catalog_entries")
@Entity
class CatalogEntryEntity {
    @Id
    var itemId: String? = null
    var title: String? = null
}

interface CatalogEntryRepository: JpaRepository<CatalogEntryEntity, String>

@Component
class CatalogEntryProjector(val repository: CatalogEntryRepository) {

    @EventHandler
    fun on(event: CatalogEntryCreatedEvent) {
        // alternativ - get the existing and create a new one if it doesn´t exists
        repository.save(CatalogEntryEntity().apply {
            itemId = event.itemId
            title = event.title
        })
    }
}