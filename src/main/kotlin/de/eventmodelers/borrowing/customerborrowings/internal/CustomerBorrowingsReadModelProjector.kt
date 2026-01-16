package de.eventmodelers.borrowing.customerborrowings.internal

import de.eventmodelers.borrowing.customerborrowings.CustomerBorrowingsReadModelEntity
import de.eventmodelers.events.ReservationMarkedAsPickedUpEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface CustomerBorrowingsReadModelRepository :
    JpaRepository<CustomerBorrowingsReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711650
*/
@Component
class CustomerBorrowingsReadModelProjector(var repository: CustomerBorrowingsReadModelRepository) {

  @EventHandler
  fun on(event: ReservationMarkedAsPickedUpEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.reservationId).orElse(CustomerBorrowingsReadModelEntity())
    entity
        .apply {
          reservationId = event.reservationId
          userId = event.userId
        }
        .also { this.repository.save(it) }
  }
}
