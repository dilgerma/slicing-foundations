package de.eventmodelers.catalog.createcatalogentry.internal

import de.eventmodelers.catalog.domain.commands.createcatalogentry.CreateCatalogEntryCommand
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class CreateCatalogEntryPayload(
    var itemId: String,
    var title: String,
    var author: String,
    var description: String,
    var isbn: String,
)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655717997017
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
    return commandGateway.send(CreateCatalogEntryCommand(itemId, title, author, description, isbn))
  }

  @CrossOrigin
  @PostMapping("/createcatalogentry/{id}")
  fun processCommand(@RequestBody payload: CreateCatalogEntryPayload): CompletableFuture<Any> {
    return commandGateway.send(
        CreateCatalogEntryCommand(
            itemId = payload.itemId,
            title = payload.title,
            author = payload.author,
            description = payload.description,
            isbn = payload.isbn,
            createdDate = LocalDateTime.now()))
  }
}
