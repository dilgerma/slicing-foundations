package de.eventmodelers.borrowing.reservebook.internal

import de.eventmodelers.borrowing.domain.commands.reservebook.ReserveBookCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class ReserveBookPayload(var bookId: String, var userId: String, var reservationId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711634
*/
@RestController
class ReserveBookResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/reservebook")
  fun processDebugCommand(
      @RequestParam bookId: String,
      @RequestParam userId: String,
      @RequestParam reservationId: String
  ): CompletableFuture<Any> {
    return commandGateway.send(ReserveBookCommand(bookId, userId, reservationId))
  }

  @CrossOrigin
  @PostMapping("/reservebook/{id}")
  fun processCommand(
      @PathVariable("id") reservationId: String,
      @RequestBody payload: ReserveBookPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        ReserveBookCommand(
            bookId = payload.bookId,
            userId = payload.userId,
            reservationId = payload.reservationId))
  }
}
