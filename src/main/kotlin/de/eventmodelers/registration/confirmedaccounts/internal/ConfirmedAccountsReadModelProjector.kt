package de.eventmodelers.registration.confirmedaccounts.internal

import de.eventmodelers.events.AccountConfirmedEvent
import de.eventmodelers.events.AccountCreatedEvent
import de.eventmodelers.events.CustomerNotifiedEvent
import de.eventmodelers.registration.confirmedaccounts.ConfirmedAccountsReadModelEntity
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ConfirmedAccountsReadModelRepository :
    JpaRepository<ConfirmedAccountsReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392353436
*/
@Component
class ConfirmedAccountsReadModelProjector(var repository: ConfirmedAccountsReadModelRepository) {

  @EventHandler
  fun on(event: AccountCreatedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.userId).orElse(ConfirmedAccountsReadModelEntity())
    entity
        .apply {
          email = event.email
          name = event.name
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: AccountConfirmedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.userId).orElse(ConfirmedAccountsReadModelEntity())
    entity
        .apply {
          email = event.email
          user_id = event.userId
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: CustomerNotifiedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.userId).orElse(ConfirmedAccountsReadModelEntity())
    entity
        .apply {
          email = event.email
          user_id = event.userId
          name = event.name
        }
        .also { this.repository.save(it) }
  }
}
