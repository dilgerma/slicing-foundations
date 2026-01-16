package de.eventmodelers.borrowing.activereservations.internal

import de.eventmodelers.borrowing.activereservations.ActiveReservationsReadModelEntity
import de.eventmodelers.events.BookReservedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ActiveReservationsReadModelRepository :
    JpaRepository<ActiveReservationsReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730201944
*/
@Component
class ActiveReservationsReadModelProjector(var repository: ActiveReservationsReadModelRepository) {

  @EventHandler
  fun on(event: BookReservedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.reservationId).orElse(ActiveReservationsReadModelEntity())
    entity
        .apply {
          bookId = event.bookId
          reservationId = event.reservationId
          userId = event.userId
        }
        .also { this.repository.save(it) }
  }
}
