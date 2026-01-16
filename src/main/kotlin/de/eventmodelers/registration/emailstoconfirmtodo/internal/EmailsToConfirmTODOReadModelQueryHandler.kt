package de.eventmodelers.registration.emailstoconfirmtodo.internal

import de.eventmodelers.registration.emailstoconfirmtodo.EmailsToConfirmTODOReadModel
import de.eventmodelers.registration.emailstoconfirmtodo.EmailsToConfirmTODOReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655601747828
*/
@Component
class EmailsToConfirmTODOReadModelQueryHandler(
    private val repository: EmailsToConfirmTODOReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: EmailsToConfirmTODOReadModelQuery): EmailsToConfirmTODOReadModel? {

    if (!repository.existsById(query.aggregateId)) {
      return null
    }
    return EmailsToConfirmTODOReadModel(repository.findById(query.aggregateId).get())
  }
}
