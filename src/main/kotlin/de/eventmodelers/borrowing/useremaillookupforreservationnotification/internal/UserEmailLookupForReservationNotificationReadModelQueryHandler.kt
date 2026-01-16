package de.eventmodelers.borrowing.useremaillookupforreservationnotification.internal

import de.eventmodelers.borrowing.useremaillookupforreservationnotification.UserEmailLookupForReservationNotificationReadModel
import de.eventmodelers.borrowing.useremaillookupforreservationnotification.UserEmailLookupForReservationNotificationReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711660
*/
@Component
class UserEmailLookupForReservationNotificationReadModelQueryHandler(
    private val repository: UserEmailLookupForReservationNotificationReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: UserEmailLookupForReservationNotificationReadModelQuery
  ): UserEmailLookupForReservationNotificationReadModel? {

    if (!repository.existsById(query.userId)) {
      return null
    }
    return UserEmailLookupForReservationNotificationReadModel(
        repository.findById(query.userId).get())
  }
}
