package de.eventmodelers.borrowing.sendreservationnotification.internal

import de.eventmodelers.borrowing.domain.commands.sendreservationnotification.SendReservationNotificationCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class SendReservationNotificationPayload(
    var email: String,
    var name: String,
    var userId: String,
    var reservationId: String
)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711641
*/
@RestController
class SendReservationNotificationResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/sendreservationnotification")
  fun processDebugCommand(
      @RequestParam email: String,
      @RequestParam name: String,
      @RequestParam userId: String,
      @RequestParam reservationId: String
  ): CompletableFuture<Any> {
    return commandGateway.send(
        SendReservationNotificationCommand(email, name, userId, reservationId))
  }

  @CrossOrigin
  @PostMapping("/sendreservationnotification/{id}")
  fun processCommand(
      @PathVariable("id") reservationId: String,
      @RequestBody payload: SendReservationNotificationPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        SendReservationNotificationCommand(
            email = payload.email,
            name = payload.name,
            userId = payload.userId,
            reservationId = payload.reservationId))
  }
}
