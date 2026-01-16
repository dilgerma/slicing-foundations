package de.eventmodelers.registration.emailstoconfirmtodo.internal

import de.eventmodelers.registration.emailstoconfirmtodo.EmailsToConfirmTODOReadModel
import de.eventmodelers.registration.emailstoconfirmtodo.EmailsToConfirmTODOReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655601747828
*/
@RestController
class EmailstoconfirmtodoResource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/emailstoconfirmtodo/{id}")
  fun findReadModel(
      @PathVariable("id") aggregateId: String
  ): CompletableFuture<EmailsToConfirmTODOReadModel> {
    return queryGateway.query(
        EmailsToConfirmTODOReadModelQuery(aggregateId), EmailsToConfirmTODOReadModel::class.java)
  }
}
