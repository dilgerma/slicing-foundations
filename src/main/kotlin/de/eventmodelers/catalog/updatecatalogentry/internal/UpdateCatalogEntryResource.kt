package de.eventmodelers.catalog.updatecatalogentry.internal

import de.eventmodelers.catalog.domain.commands.updatecatalogentry.UpdateCatalogEntryCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class UpdateCatalogEntryPayload(
    var author: String,
    var description: String,
    var itemId: String,
    var title: String
)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655717997160
*/
@RestController
class UpdateCatalogEntryResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/updatecatalogentry")
  fun processDebugCommand(
      @RequestParam author: String,
      @RequestParam description: String,
      @RequestParam itemId: String,
      @RequestParam title: String
  ): CompletableFuture<Any> {
    return commandGateway.send(UpdateCatalogEntryCommand(author, description, itemId, title))
  }

  @CrossOrigin
  @PostMapping("/updatecatalogentry/{id}")
  fun processCommand(
      @PathVariable("id") itemId: String,
      @RequestBody payload: UpdateCatalogEntryPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        UpdateCatalogEntryCommand(
            author = payload.author,
            description = payload.description,
            itemId = payload.itemId,
            title = payload.title))
  }
}
