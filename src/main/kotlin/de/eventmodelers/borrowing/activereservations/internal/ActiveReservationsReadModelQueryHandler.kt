package de.eventmodelers.borrowing.activereservations.internal

import de.eventmodelers.borrowing.activereservations.ActiveReservationsReadModel
import de.eventmodelers.borrowing.activereservations.ActiveReservationsReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730201944
*/
@Component
class ActiveReservationsReadModelQueryHandler(
    private val repository: ActiveReservationsReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: ActiveReservationsReadModelQuery): ActiveReservationsReadModel? {
    return ActiveReservationsReadModel(repository.findAll())
  }
}
