package de.eventmodelers.registration.confirmationmailstosend.internal

import de.eventmodelers.registration.confirmationmailstosend.ConfirmationMailsToSendTODOReadModel
import de.eventmodelers.registration.confirmationmailstosend.ConfirmationMailsToSendTODOReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010052
*/
@RestController
class ConfirmationmailstosendResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/confirmationmailstosend")
  fun findReadModel(): CompletableFuture<ConfirmationMailsToSendTODOReadModel> {
    return queryGateway.query(
        ConfirmationMailsToSendTODOReadModelQuery(),
        ConfirmationMailsToSendTODOReadModel::class.java)
  }
}
