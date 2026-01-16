package de.eventmodelers.borrowing.customerborrowings.internal

import de.eventmodelers.borrowing.customerborrowings.CustomerBorrowingsReadModel
import de.eventmodelers.borrowing.customerborrowings.CustomerBorrowingsReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711650
*/
@RestController
class CustomerborrowingsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/customerborrowings")
  fun findReadModel(): CompletableFuture<CustomerBorrowingsReadModel> {
    return queryGateway.query(
        CustomerBorrowingsReadModelQuery(), CustomerBorrowingsReadModel::class.java)
  }
}
