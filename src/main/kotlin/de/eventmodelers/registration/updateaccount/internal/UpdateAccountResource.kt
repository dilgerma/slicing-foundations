package de.eventmodelers.registration.updateaccount.internal

import de.eventmodelers.registration.domain.commands.updateaccount.UpdateAccountCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class UpdateAccountPayload(var name: String, var email: String, var user_id: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392353799
*/
@RestController
class UpdateAccountResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/updateaccount")
  fun processDebugCommand(
      @RequestParam name: String,
      @RequestParam email: String,
      @RequestParam user_id: String
  ): CompletableFuture<Any> {
    return commandGateway.send(UpdateAccountCommand(name, email, user_id))
  }

  @CrossOrigin
  @PostMapping("/updateaccount/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: UpdateAccountPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        UpdateAccountCommand(name = payload.name, email = payload.email, user_id = payload.user_id))
  }
}
