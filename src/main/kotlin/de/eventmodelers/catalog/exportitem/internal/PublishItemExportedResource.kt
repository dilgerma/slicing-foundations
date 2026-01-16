package de.eventmodelers.catalog.exportitem.internal

import de.eventmodelers.catalog.domain.commands.exportitem.PublishItemExportedCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ExportItemPayload(
    var author: String,
    var description: String,
    var itemId: String,
    var title: String
)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655738191723
*/
@RestController
class PublishItemExportedResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/exportitem")
  fun processDebugCommand(
      @RequestParam author: String,
      @RequestParam description: String,
      @RequestParam itemId: String,
      @RequestParam title: String
  ): CompletableFuture<Any> {
    return commandGateway.send(PublishItemExportedCommand(author, description, itemId, title))
  }

  @CrossOrigin
  @PostMapping("/exportitem/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: ExportItemPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        PublishItemExportedCommand(
            author = payload.author,
            description = payload.description,
            itemId = payload.itemId,
            title = payload.title))
  }
}
