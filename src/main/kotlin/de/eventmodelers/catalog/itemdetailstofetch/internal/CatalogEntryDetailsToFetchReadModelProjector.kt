package de.eventmodelers.catalog.itemdetailstofetch.internal

import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.UUID
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import de.eventmodelers.events.CatalogEntryInformationAddedEvent
import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModelEntity


import mu.KotlinLogging

interface CatalogEntryDetailsToFetchReadModelRepository :
    JpaRepository<CatalogEntryDetailsToFetchReadModelEntity, String>

/* 
        // AI-TODO:
        
# Spec Start
Title: spec: Item Details to fetch is empty when fetched
### Given (Events):
  * 'Item Information added' (SPEC_EVENT)
Fields:
 - itemId: 
 - title: 
 - author: 
 - description: 
 - isbn: 
  * 'Catalogue entry created' (SPEC_EVENT)
Fields:
 - author: 
 - description: 
 - isbn: 
 - itemId: 
 - title: 
### When (Command): None
### Then:
  * 'Item Details to fetch' (SPEC_READMODEL)
Fields:
 - fetched: 
 - itemId: 
 - isbn: 
# Spec End

# Spec Start
Title: spec: Item Details to fetch - scenario
### Given (Events):
  * 'Catalogue entry created' (SPEC_EVENT)
Fields:
 - author: 
 - description: 
 - isbn: 
 - itemId: 1
 - title: 
### When (Command): None
### Then:
  * 'Item Details to fetch' (SPEC_READMODEL)
Fields:
 - fetched: false
 - itemId: 1
 - isbn: 
# Spec End */
/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115509
*/
@Component
class CatalogEntryDetailsToFetchReadModelProjector(
    var repository: CatalogEntryDetailsToFetchReadModelRepository
) {


    @EventHandler
    fun on(event: CatalogueEntryCreatedEvent) {
        //throws exception if not available (adjust logic)
        val entity = this.repository.findById(event.itemId).orElse(CatalogEntryDetailsToFetchReadModelEntity())
        entity.apply {
            itemId = event.itemId
            isbn = event.isbn
        }.also { this.repository.save(it) }
    }

    @EventHandler
    fun on(event: CatalogEntryInformationAddedEvent) {
        repository.deleteById(event.itemId)
    }

}
