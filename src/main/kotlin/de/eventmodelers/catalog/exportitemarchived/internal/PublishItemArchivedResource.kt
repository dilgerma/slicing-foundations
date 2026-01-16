package de.eventmodelers.catalog.exportitemarchived.internal

import de.eventmodelers.catalog.domain.commands.exportitemarchived.PublishItemArchivedCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ExportItemArchivedPayload(var itemId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655738738248
*/
@RestController
class PublishItemArchivedResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/exportitemarchived")
  fun processDebugCommand(@RequestParam itemId: String): CompletableFuture<Any> {
    return commandGateway.send(PublishItemArchivedCommand(itemId))
  }

  @CrossOrigin
  @PostMapping("/exportitemarchived/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: ExportItemArchivedPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(PublishItemArchivedCommand(itemId = payload.itemId))
  }
}
