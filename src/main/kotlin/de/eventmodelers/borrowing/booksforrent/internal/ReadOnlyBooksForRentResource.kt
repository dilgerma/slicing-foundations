package de.eventmodelers.borrowing.booksforrent.internal

import de.eventmodelers.borrowing.booksforrent.BooksForRentReadModel
import de.eventmodelers.borrowing.booksforrent.BooksForRentReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711659
*/
@RestController
class BooksforrentResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/booksforrent/{id}")
  fun findReadModel(@PathVariable("id") bookId: String): CompletableFuture<BooksForRentReadModel> {
    return queryGateway.query(BooksForRentReadModelQuery(bookId), BooksForRentReadModel::class.java)
  }
}
