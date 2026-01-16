package de.eventmodelers.registration.deactivateaccount.internal

import de.eventmodelers.registration.domain.commands.deactivateaccount.DeactivateAccountCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class DeactivateAccountPayload(var user_id: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655393190353
*/
@RestController
class DeactivateAccountResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/deactivateaccount")
  fun processDebugCommand(@RequestParam user_id: String): CompletableFuture<Any> {
    return commandGateway.send(DeactivateAccountCommand(user_id))
  }

  @CrossOrigin
  @PostMapping("/deactivateaccount/{id}")
  fun processCommand(
      @PathVariable("id") aggregateId: java.util.UUID,
      @RequestBody payload: DeactivateAccountPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(DeactivateAccountCommand(user_id = payload.user_id))
  }
}
