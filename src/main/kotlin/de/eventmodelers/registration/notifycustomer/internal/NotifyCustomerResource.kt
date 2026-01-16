package de.eventmodelers.registration.notifycustomer.internal

import de.eventmodelers.registration.domain.commands.notifycustomer.NotifyCustomerCommand
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

data class NotifyCustomerPayload(
    var email: String,
    var name: String,
    var userId: String,
    var token: String
)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010383
*/
@RestController
class NotifyCustomerResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/notifycustomer")
  fun processDebugCommand(
      @RequestParam email: String,
      @RequestParam name: String,
      @RequestParam userId: String,
      @RequestParam token: String
  ): CompletableFuture<Any> {
    return commandGateway.send(NotifyCustomerCommand(email, name, userId, token))
  }

  @CrossOrigin
  @PostMapping("/notifycustomer/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: NotifyCustomerPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        NotifyCustomerCommand(
            email = payload.email,
            name = payload.name,
            userId = payload.userId,
            token = payload.token))
  }
}
