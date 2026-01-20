package de.eventmodelers.catalog.catalogentries.internal


import de.eventmodelers.domain.createcatalogentry.CreateCatalogEntryCommand
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture


/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115383
*/
@RestController
class CatalogEntriesResource(private var repository: CatalogEntryRepository) {

    var logger = KotlinLogging.logger {}


    @CrossOrigin
    @GetMapping("/catalogentries")
    fun provideEntries(): List<CatalogEntryEntity> {
        return repository.findAll()
    }


}
