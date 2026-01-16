package de.eventmodelers.borrowing.markdamaged.internal

import de.eventmodelers.borrowing.domain.commands.markdamaged.MarkDamagedCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class MarkDamagedPayload(var bookId: String, var reservationId: String, var userId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711656
*/
@RestController
class MarkDamagedResource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/markdamaged")
  fun processDebugCommand(
      @RequestParam bookId: String,
      @RequestParam reservationId: String,
      @RequestParam userId: String
  ): CompletableFuture<Any> {
    return commandGateway.send(MarkDamagedCommand(bookId, reservationId, userId))
  }

  @CrossOrigin
  @PostMapping("/markdamaged/{id}")
  fun processCommand(
      @PathVariable("id") reservationId: String,
      @RequestBody payload: MarkDamagedPayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        MarkDamagedCommand(
            bookId = payload.bookId,
            reservationId = payload.reservationId,
            userId = payload.userId))
  }
}
