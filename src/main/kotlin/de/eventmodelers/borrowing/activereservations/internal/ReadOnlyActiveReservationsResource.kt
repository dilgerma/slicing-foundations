package de.eventmodelers.borrowing.activereservations.internal

import de.eventmodelers.borrowing.activereservations.ActiveReservationsReadModel
import de.eventmodelers.borrowing.activereservations.ActiveReservationsReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730201944
*/
@RestController
class ActivereservationsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/activereservations")
  fun findReadModel(): CompletableFuture<ActiveReservationsReadModel> {
    return queryGateway.query(
        ActiveReservationsReadModelQuery(), ActiveReservationsReadModel::class.java)
  }
}
