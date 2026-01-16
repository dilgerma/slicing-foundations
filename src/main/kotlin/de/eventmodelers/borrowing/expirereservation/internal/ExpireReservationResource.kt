package de.eventmodelers.borrowing.expirereservation.internal

import de.eventmodelers.borrowing.domain.commands.expirereservation.ExpireReservationCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ExpireReservationPayload(var reservationId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711644
*/
@RestController
class ExpireReservationResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/expirereservation")
  fun processDebugCommand(@RequestParam reservationId: String): CompletableFuture<Any> {
    return commandGateway.send(ExpireReservationCommand(reservationId))
  }

  @CrossOrigin
  @PostMapping("/expirereservation/{id}")
  fun processCommand(
      @PathVariable("id") reservationId: String,
      @RequestBody payload: ExpireReservationPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(ExpireReservationCommand(reservationId = payload.reservationId))
  }
}
