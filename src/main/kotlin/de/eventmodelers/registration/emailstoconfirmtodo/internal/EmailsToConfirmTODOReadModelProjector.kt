package de.eventmodelers.registration.emailstoconfirmtodo.internal

import de.eventmodelers.events.CustomerNotifiedEvent
import de.eventmodelers.registration.emailstoconfirmtodo.EmailsToConfirmTODOReadModelEntity
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface EmailsToConfirmTODOReadModelRepository :
    JpaRepository<EmailsToConfirmTODOReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655601747828
*/
@Component
class EmailsToConfirmTODOReadModelProjector(
    var repository: EmailsToConfirmTODOReadModelRepository
) {

  @EventHandler
  fun on(event: CustomerNotifiedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.userId).orElse(EmailsToConfirmTODOReadModelEntity())
    entity
        .apply {
          email = event.email
          token = event.token
          userId = event.userId
        }
        .also { this.repository.save(it) }
  }
}
