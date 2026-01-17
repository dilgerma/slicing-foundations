package de.eventmodelers.catalog.addmissingdata.internal

import de.eventmodelers.catalog.domain.commands.addmissingdata.AddMissingDataCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class AddMissingDataPayload(
    var itemId: String,
    var title: String,
    var author: String,
    var description: String
)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872720
*/
@RestController
class AddMissingDataResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/addmissingdata")
  fun processDebugCommand(
      @RequestParam itemId: String,
      @RequestParam title: String,
      @RequestParam author: String,
      @RequestParam description: String
  ): CompletableFuture<Any> {
    return commandGateway.send(AddMissingDataCommand(itemId, title, author, description))
  }

  @CrossOrigin
  @PostMapping("/addmissingdata/{id}")
  fun processCommand(
      @PathVariable("id") itemId: String,
      @RequestBody payload: AddMissingDataPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        AddMissingDataCommand(
            itemId = payload.itemId,
            title = payload.title,
            author = payload.author,
            description = payload.description))
  }
}
