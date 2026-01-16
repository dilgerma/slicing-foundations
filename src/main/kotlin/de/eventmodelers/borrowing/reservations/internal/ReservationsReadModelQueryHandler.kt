package de.eventmodelers.borrowing.reservations.internal

import de.eventmodelers.borrowing.reservations.AllReservationsReadModelQuery
import de.eventmodelers.borrowing.reservations.ReservationsReadModel
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711635
*/
@Component
class ReservationsReadModelQueryHandler(private val repository: ReservationsReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: AllReservationsReadModelQuery): ReservationsReadModel? {

    return ReservationsReadModel(repository.findAll())
  }
}
