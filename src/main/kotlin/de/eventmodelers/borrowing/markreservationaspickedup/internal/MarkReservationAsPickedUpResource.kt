package de.eventmodelers.borrowing.markreservationaspickedup.internal

import de.eventmodelers.borrowing.domain.commands.markreservationaspickedup.MarkReservationAsPickedUpCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class MarkReservationAsPickedUpPayload(var reservationId: String, var userId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711649
*/
@RestController
class MarkReservationAsPickedUpResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/markreservationaspickedup")
  fun processDebugCommand(
      @RequestParam reservationId: String,
      @RequestParam userId: String
  ): CompletableFuture<Any> {
    return commandGateway.send(MarkReservationAsPickedUpCommand(reservationId, userId))
  }

  @CrossOrigin
  @PostMapping("/markreservationaspickedup/{id}")
  fun processCommand(
      @PathVariable("id") reservationId: String,
      @RequestBody payload: MarkReservationAsPickedUpPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        MarkReservationAsPickedUpCommand(
            reservationId = payload.reservationId, userId = payload.userId))
  }
}
