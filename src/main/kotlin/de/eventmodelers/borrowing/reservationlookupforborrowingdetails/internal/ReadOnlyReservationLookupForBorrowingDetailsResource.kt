package de.eventmodelers.borrowing.reservationlookupforborrowingdetails.internal

import de.eventmodelers.borrowing.reservationlookupforborrowingdetails.ReservationLookupForBorrowingDetailsReadModel
import de.eventmodelers.borrowing.reservationlookupforborrowingdetails.ReservationLookupForBorrowingDetailsReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742424589
*/
@RestController
class ReservationlookupforborrowingdetailsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/reservationlookupforborrowingdetails/{id}")
  fun findReadModel(
      @PathVariable("id") reservationId: String
  ): CompletableFuture<ReservationLookupForBorrowingDetailsReadModel> {
    return queryGateway.query(
        ReservationLookupForBorrowingDetailsReadModelQuery(reservationId),
        ReservationLookupForBorrowingDetailsReadModel::class.java)
  }
}
