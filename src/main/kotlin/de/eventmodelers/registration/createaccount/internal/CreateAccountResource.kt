package de.eventmodelers.registration.createaccount.internal

import de.eventmodelers.registration.domain.commands.createaccount.CreateAccountCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class CreateAccountPayload(var userId: String, var email: String, var name: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655390776063
*/
@RestController
class CreateAccountResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/createaccount")
  fun processDebugCommand(
      @RequestParam userId: String,
      @RequestParam email: String,
      @RequestParam name: String
  ): CompletableFuture<Any> {
    return commandGateway.send(CreateAccountCommand(userId, email, name))
  }

  @CrossOrigin
  @PostMapping("/createaccount/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: String,
      @RequestBody payload: CreateAccountPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        CreateAccountCommand(userId = payload.userId, email = payload.email, name = payload.name))
  }
}
