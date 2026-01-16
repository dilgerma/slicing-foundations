package de.eventmodelers.catalog.archivecatalogentry.internal

import de.eventmodelers.catalog.domain.commands.archivecatalogentry.ArchiveCatalogEntryCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ArchiveCatalogEntryPayload(var itemId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655717997216
*/
@RestController
class ArchiveCatalogEntryResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/archivecatalogentry")
  fun processDebugCommand(@RequestParam itemId: String): CompletableFuture<Any> {
    return commandGateway.send(ArchiveCatalogEntryCommand(itemId))
  }

  @CrossOrigin
  @PostMapping("/archivecatalogentry/{id}")
  fun processCommand(
      @PathVariable("id") itemId: String,
      @RequestBody payload: ArchiveCatalogEntryPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(ArchiveCatalogEntryCommand(itemId = payload.itemId))
  }
}
