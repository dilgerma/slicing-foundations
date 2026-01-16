package de.eventmodelers.registration.confirmationmailstosend.internal

import de.eventmodelers.registration.confirmationmailstosend.ConfirmationMailsToSendTODOReadModel
import de.eventmodelers.registration.confirmationmailstosend.ConfirmationMailsToSendTODOReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010052
*/
@Component
class ConfirmationMailsToSendTODOReadModelQueryHandler(
    private val repository: ConfirmationMailsToSendTODOReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: ConfirmationMailsToSendTODOReadModelQuery
  ): ConfirmationMailsToSendTODOReadModel? {
    return ConfirmationMailsToSendTODOReadModel(repository.findAll())
  }
}
