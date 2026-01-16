package de.eventmodelers.catalog.publishitem.internal

import de.eventmodelers.catalog.domain.commands.publishitem.PublishItemCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class PublishItemPayload(var itemId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655732594496
*/
@RestController
class PublishItemResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/publishitem")
  fun processDebugCommand(@RequestParam itemId: String): CompletableFuture<Any> {
    return commandGateway.send(PublishItemCommand(itemId))
  }

  @CrossOrigin
  @PostMapping("/publishitem/{id}")
  fun processCommand(
      @PathVariable("id") itemId: String,
      @RequestBody payload: PublishItemPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(PublishItemCommand(itemId = payload.itemId))
  }
}
