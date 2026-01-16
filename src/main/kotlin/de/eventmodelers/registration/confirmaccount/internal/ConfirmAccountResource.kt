package de.eventmodelers.registration.confirmaccount.internal

import de.eventmodelers.registration.domain.commands.confirmaccount.ConfirmAccountCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ConfirmAccountPayload(var email: String, var user_id: String, var token: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392004706
*/
@RestController
class ConfirmAccountResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/confirmaccount")
  fun processDebugCommand(
      @RequestParam email: String,
      @RequestParam user_id: String,
      @RequestParam token: String
  ): CompletableFuture<Any> {
    return commandGateway.send(ConfirmAccountCommand(email, user_id, token))
  }

  @CrossOrigin
  @PostMapping("/confirmaccount/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: ConfirmAccountPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        ConfirmAccountCommand(
            email = payload.email, user_id = payload.user_id, token = payload.token))
  }
}
