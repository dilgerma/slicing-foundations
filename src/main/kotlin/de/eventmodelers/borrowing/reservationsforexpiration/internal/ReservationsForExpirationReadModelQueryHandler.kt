package de.eventmodelers.borrowing.reservationsforexpiration.internal

import de.eventmodelers.borrowing.reservationsforexpiration.ReservationsForExpirationReadModel
import de.eventmodelers.borrowing.reservationsforexpiration.ReservationsForExpirationReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711645
*/
@Component
class ReservationsForExpirationReadModelQueryHandler(
    private val repository: ReservationsForExpirationReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: ReservationsForExpirationReadModelQuery
  ): ReservationsForExpirationReadModel? {

    if (!repository.existsById(query.reservationId)) {
      return null
    }
    return ReservationsForExpirationReadModel(repository.findById(query.reservationId).get())
  }
}
