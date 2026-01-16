package de.eventmodelers.registration.confirmedaccounts.internal

import de.eventmodelers.registration.confirmedaccounts.ConfirmedAccountsReadModel
import de.eventmodelers.registration.confirmedaccounts.ConfirmedAccountsReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392353436
*/
@Component
class ConfirmedAccountsReadModelQueryHandler(
    private val repository: ConfirmedAccountsReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: ConfirmedAccountsReadModelQuery): ConfirmedAccountsReadModel? {
    return ConfirmedAccountsReadModel(repository.findAll())
  }
}
