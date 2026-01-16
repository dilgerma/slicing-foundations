package de.eventmodelers.borrowing.reservationnotificationtosend.internal

import de.eventmodelers.borrowing.reservationnotificationtosend.ReservationNotificationToSendReadModel
import de.eventmodelers.borrowing.reservationnotificationtosend.ReservationNotificationToSendReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711638
*/
@RestController
class ReservationnotificationtosendResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/reservationnotificationtosend/{id}")
  fun findReadModel(
      @PathVariable("id") reservationId: String
  ): CompletableFuture<ReservationNotificationToSendReadModel> {
    return queryGateway.query(
        ReservationNotificationToSendReadModelQuery(reservationId),
        ReservationNotificationToSendReadModel::class.java)
  }
}
