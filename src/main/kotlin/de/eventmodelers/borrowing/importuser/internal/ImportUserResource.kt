package de.eventmodelers.borrowing.importuser.internal

import de.eventmodelers.borrowing.domain.commands.importuser.ImportUserCommand
import java.util.UUID
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ImportUserPayload(var userId: UUID, var email: String, var name: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711665
*/
@RestController
class ImportUserResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/importuser")
  fun processDebugCommand(
      @RequestParam userId: UUID,
      @RequestParam email: String,
      @RequestParam name: String
  ): CompletableFuture<Any> {
    return commandGateway.send(ImportUserCommand(userId, email, name))
  }

  @CrossOrigin
  @PostMapping("/importuser/{id}")
  fun processCommand(
      @PathVariable("id") userId: UUID,
      @RequestBody payload: ImportUserPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        ImportUserCommand(userId = payload.userId, email = payload.email, name = payload.name))
  }
}
