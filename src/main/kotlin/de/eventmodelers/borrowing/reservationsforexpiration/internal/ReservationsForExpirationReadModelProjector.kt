package de.eventmodelers.borrowing.reservationsforexpiration.internal

import de.eventmodelers.borrowing.reservationsforexpiration.ReservationsForExpirationReadModelEntity
import de.eventmodelers.events.BookReservedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ReservationsForExpirationReadModelRepository :
    JpaRepository<ReservationsForExpirationReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711645
*/
@Component
class ReservationsForExpirationReadModelProjector(
    var repository: ReservationsForExpirationReadModelRepository
) {

  @EventHandler
  fun on(event: BookReservedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository
            .findById(event.reservationId)
            .orElse(ReservationsForExpirationReadModelEntity())
    entity
        .apply {
          bookId = event.bookId
          reservationId = event.reservationId
          userId = event.userId
        }
        .also { this.repository.save(it) }
  }
}
