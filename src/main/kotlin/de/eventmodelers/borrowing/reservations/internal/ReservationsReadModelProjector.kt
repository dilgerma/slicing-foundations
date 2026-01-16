package de.eventmodelers.borrowing.reservations.internal

import de.eventmodelers.borrowing.reservations.ReservationsReadModelEntity
import de.eventmodelers.borrowing.reservations.ReservationsReadModelKey
import de.eventmodelers.events.BookReservedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ReservationsReadModelRepository :
    JpaRepository<ReservationsReadModelEntity, ReservationsReadModelKey>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711635
*/
@Component
class ReservationsReadModelProjector(var repository: ReservationsReadModelRepository) {

  @EventHandler
  fun on(event: BookReservedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository
            .findById(ReservationsReadModelKey(event.bookId, event.userId))
            .orElse(ReservationsReadModelEntity())
    entity
        .apply {
          bookId = event.bookId
          userId = event.userId
        }
        .also { this.repository.save(it) }
  }
}
