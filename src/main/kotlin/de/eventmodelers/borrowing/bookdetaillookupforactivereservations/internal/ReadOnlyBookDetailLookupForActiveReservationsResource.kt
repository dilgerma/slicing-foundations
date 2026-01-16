package de.eventmodelers.borrowing.bookdetaillookupforactivereservations.internal

import de.eventmodelers.borrowing.bookdetaillookupforactivereservations.BookDetailLookupForActiveReservationsReadModel
import de.eventmodelers.borrowing.bookdetaillookupforactivereservations.BookDetailLookupForActiveReservationsReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730751939
*/
@RestController
class BookdetaillookupforactivereservationsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/bookdetaillookupforactivereservations")
  fun findReadModel(): CompletableFuture<BookDetailLookupForActiveReservationsReadModel> {
    return queryGateway.query(
        BookDetailLookupForActiveReservationsReadModelQuery(),
        BookDetailLookupForActiveReservationsReadModel::class.java)
  }
}
