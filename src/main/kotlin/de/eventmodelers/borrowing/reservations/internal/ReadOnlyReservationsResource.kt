package de.eventmodelers.borrowing.reservations.internal

import de.eventmodelers.borrowing.reservations.AllReservationsReadModelQuery
import de.eventmodelers.borrowing.reservations.ReservationsReadModel
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711635
*/
@RestController
class ReservationsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/reservations")
  fun findReadModel(): CompletableFuture<ReservationsReadModel> {
    return queryGateway.query(AllReservationsReadModelQuery(), ReservationsReadModel::class.java)
  }
}
