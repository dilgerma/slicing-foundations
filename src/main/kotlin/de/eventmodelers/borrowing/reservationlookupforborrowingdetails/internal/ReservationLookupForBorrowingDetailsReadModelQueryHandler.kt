package de.eventmodelers.borrowing.reservationlookupforborrowingdetails.internal

import de.eventmodelers.borrowing.reservationlookupforborrowingdetails.ReservationLookupForBorrowingDetailsReadModel
import de.eventmodelers.borrowing.reservationlookupforborrowingdetails.ReservationLookupForBorrowingDetailsReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742424589
*/
@Component
class ReservationLookupForBorrowingDetailsReadModelQueryHandler(
    private val repository: ReservationLookupForBorrowingDetailsReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: ReservationLookupForBorrowingDetailsReadModelQuery
  ): ReservationLookupForBorrowingDetailsReadModel? {

    if (!repository.existsById(query.reservationId)) {
      return null
    }
    return ReservationLookupForBorrowingDetailsReadModel(
        repository.findById(query.reservationId).get())
  }
}
