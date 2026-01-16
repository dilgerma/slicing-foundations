package de.eventmodelers.registration.confirmedaccounts.internal

import de.eventmodelers.registration.confirmedaccounts.ConfirmedAccountsReadModel
import de.eventmodelers.registration.confirmedaccounts.ConfirmedAccountsReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392353436
*/
@RestController
class ConfirmedaccountsResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/confirmedaccounts")
  fun findReadModel(): CompletableFuture<ConfirmedAccountsReadModel> {
    return queryGateway.query(
        ConfirmedAccountsReadModelQuery(), ConfirmedAccountsReadModel::class.java)
  }
}
