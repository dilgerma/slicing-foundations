package de.eventmodelers.borrowing.marklost.internal

import de.eventmodelers.borrowing.domain.commands.marklost.MarkLostCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class MarkLostPayload(var userId: String, var bookId: String, var reservationId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711653
*/
@RestController
class MarkLostResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/marklost")
  fun processDebugCommand(
      @RequestParam userId: String,
      @RequestParam bookId: String,
      @RequestParam reservationId: String
  ): CompletableFuture<Any> {
    return commandGateway.send(MarkLostCommand(userId, bookId, reservationId))
  }

  @CrossOrigin
  @PostMapping("/marklost/{id}")
  fun processCommand(
      @PathVariable("id") reservationId: String,
      @RequestBody payload: MarkLostPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        MarkLostCommand(
            userId = payload.userId,
            bookId = payload.bookId,
            reservationId = payload.reservationId))
  }
}
