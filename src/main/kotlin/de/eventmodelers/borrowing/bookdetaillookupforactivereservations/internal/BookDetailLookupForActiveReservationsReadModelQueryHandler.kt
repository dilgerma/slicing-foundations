package de.eventmodelers.borrowing.bookdetaillookupforactivereservations.internal

import de.eventmodelers.borrowing.bookdetaillookupforactivereservations.BookDetailLookupForActiveReservationsReadModel
import de.eventmodelers.borrowing.bookdetaillookupforactivereservations.BookDetailLookupForActiveReservationsReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730751939
*/
@Component
class BookDetailLookupForActiveReservationsReadModelQueryHandler(
    private val repository: BookDetailLookupForActiveReservationsReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: BookDetailLookupForActiveReservationsReadModelQuery
  ): BookDetailLookupForActiveReservationsReadModel? {
    return BookDetailLookupForActiveReservationsReadModel(repository.findAll())
  }
}
