package de.eventmodelers.borrowing.useremaillookupforreservationnotification.internal

import de.eventmodelers.borrowing.useremaillookupforreservationnotification.UserEmailLookupForReservationNotificationReadModel
import de.eventmodelers.borrowing.useremaillookupforreservationnotification.UserEmailLookupForReservationNotificationReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711660
*/
@RestController
class UseremaillookupforreservationnotificationResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/useremaillookupforreservationnotification/{id}")
  fun findReadModel(
      @PathVariable("id") userId: String
  ): CompletableFuture<UserEmailLookupForReservationNotificationReadModel> {
    return queryGateway.query(
        UserEmailLookupForReservationNotificationReadModelQuery(userId),
        UserEmailLookupForReservationNotificationReadModel::class.java)
  }
}
