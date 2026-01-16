package de.eventmodelers.borrowing.reservationsforexpiration.internal

import de.eventmodelers.borrowing.reservationsforexpiration.ReservationsForExpirationReadModel
import de.eventmodelers.borrowing.reservationsforexpiration.ReservationsForExpirationReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711645
*/
@RestController
class ReservationsforexpirationResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/reservationsforexpiration/{id}")
  fun findReadModel(
      @PathVariable("id") reservationId: String
  ): CompletableFuture<ReservationsForExpirationReadModel> {
    return queryGateway.query(
        ReservationsForExpirationReadModelQuery(reservationId),
        ReservationsForExpirationReadModel::class.java)
  }
}
