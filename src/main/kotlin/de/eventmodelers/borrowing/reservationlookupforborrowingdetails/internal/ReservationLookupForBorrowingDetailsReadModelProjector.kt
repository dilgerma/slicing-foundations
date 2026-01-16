package de.eventmodelers.borrowing.reservationlookupforborrowingdetails.internal

import de.eventmodelers.borrowing.reservationlookupforborrowingdetails.ReservationLookupForBorrowingDetailsReadModelEntity
import de.eventmodelers.events.BookReservedEvent
import de.eventmodelers.events.ReservationMarkedAsPickedUpEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ReservationLookupForBorrowingDetailsReadModelRepository :
    JpaRepository<ReservationLookupForBorrowingDetailsReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742424589
*/
@Component
class ReservationLookupForBorrowingDetailsReadModelProjector(
    var repository: ReservationLookupForBorrowingDetailsReadModelRepository
) {

  @EventHandler
  fun on(event: BookReservedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository
            .findById(event.reservationId)
            .orElse(ReservationLookupForBorrowingDetailsReadModelEntity())
    entity
        .apply {
          bookId = event.bookId
          reservationId = event.reservationId
          userId = event.userId
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: ReservationMarkedAsPickedUpEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository
            .findById(event.reservationId)
            .orElse(ReservationLookupForBorrowingDetailsReadModelEntity())
    entity
        .apply {
          reservationId = event.reservationId
          userId = event.userId
        }
        .also { this.repository.save(it) }
  }
}
