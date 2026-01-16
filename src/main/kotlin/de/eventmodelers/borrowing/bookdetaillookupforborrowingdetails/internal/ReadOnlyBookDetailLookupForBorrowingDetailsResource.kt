package de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.internal

import de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.BookDetailLookupForBorrowingDetailsReadModel
import de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.BookDetailLookupForBorrowingDetailsReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742569625
*/
@RestController
class BookdetaillookupforborrowingdetailsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/bookdetaillookupforborrowingdetails")
  fun findReadModel(): CompletableFuture<BookDetailLookupForBorrowingDetailsReadModel> {
    return queryGateway.query(
        BookDetailLookupForBorrowingDetailsReadModelQuery(),
        BookDetailLookupForBorrowingDetailsReadModel::class.java)
  }
}
