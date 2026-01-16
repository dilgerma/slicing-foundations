package de.eventmodelers.borrowing.reservationnotificationtosend.internal

import de.eventmodelers.borrowing.reservationnotificationtosend.ReservationNotificationToSendReadModelEntity
import de.eventmodelers.events.BookReservedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ReservationNotificationToSendReadModelRepository :
    JpaRepository<ReservationNotificationToSendReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711638
*/
@Component
class ReservationNotificationToSendReadModelProjector(
    var repository: ReservationNotificationToSendReadModelRepository
) {

  @EventHandler
  fun on(event: BookReservedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository
            .findById(event.reservationId)
            .orElse(ReservationNotificationToSendReadModelEntity())
    entity
        .apply {
          bookId = event.bookId
          userId = event.userId
          reservationId = event.reservationId
        }
        .also { this.repository.save(it) }
  }
}
