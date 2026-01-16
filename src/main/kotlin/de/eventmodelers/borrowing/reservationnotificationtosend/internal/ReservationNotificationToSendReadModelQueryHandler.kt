package de.eventmodelers.borrowing.reservationnotificationtosend.internal

import de.eventmodelers.borrowing.reservationnotificationtosend.ReservationNotificationToSendReadModel
import de.eventmodelers.borrowing.reservationnotificationtosend.ReservationNotificationToSendReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711638
*/
@Component
class ReservationNotificationToSendReadModelQueryHandler(
    private val repository: ReservationNotificationToSendReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: ReservationNotificationToSendReadModelQuery
  ): ReservationNotificationToSendReadModel? {

    if (!repository.existsById(query.reservationId)) {
      return null
    }
    return ReservationNotificationToSendReadModel(repository.findById(query.reservationId).get())
  }
}
