package de.eventmodelers.catalog.createcatalogentry.internal


import de.eventmodelers.domain.createcatalogentry.CreateCatalogEntryCommand
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.*
import java.util.UUID
import java.util.concurrent.CompletableFuture


data class CreateCatalogEntryPayload(
    var itemId: String,
    var title: String,
    var author: String,
    var description: String,
    var isbn: String
)

/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115383
*/
@RestController
class CreateCatalogEntryResource(private var commandGateway: CommandGateway) {

    var logger = KotlinLogging.logger {}


    @CrossOrigin
    @PostMapping("/debug/createcatalogentry")
    fun processDebugCommand(
        @RequestParam itemId: String,
        @RequestParam title: String,
        @RequestParam author: String,
        @RequestParam description: String,
        @RequestParam isbn: String
    ): CompletableFuture<Any> {
        return commandGateway.send(
            CreateCatalogEntryCommand(
                itemId,
                title,
                author,
                description,
                isbn
            )
        )
    }


    @CrossOrigin
    @PostMapping("/createcatalogentry/{id}")
    fun processCommand(
        @PathVariable("id") itemId: String,
        @RequestBody payload: CreateCatalogEntryPayload
    ): CompletableFuture<Any> {
        return commandGateway.send(
			CreateCatalogEntryCommand(
				itemId = UUID.randomUUID().toString(),
				title = payload.title,
				author = payload.author,
				description = payload.description,
				isbn = payload.isbn
			)
        )
    }


}
