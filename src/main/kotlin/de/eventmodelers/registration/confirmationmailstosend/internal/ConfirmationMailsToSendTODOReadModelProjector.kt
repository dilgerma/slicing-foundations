package de.eventmodelers.registration.confirmationmailstosend.internal

import de.eventmodelers.events.AccountCreatedEvent
import de.eventmodelers.events.CustomerNotifiedEvent
import de.eventmodelers.registration.confirmationmailstosend.ConfirmationMailsToSendTODOReadModelEntity
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface ConfirmationMailsToSendTODOReadModelRepository :
    JpaRepository<ConfirmationMailsToSendTODOReadModelEntity, String>

/*

# Spec Start
Title: spec: Confirmation Mails to send empty after confirmed
### Given (Events):
  * 'Customer notified' (SPEC_EVENT)
Fields:
 - user_id:
 - email:
 - name:
 - token:
  * 'Account created' (SPEC_EVENT)
Fields:
 - user id:
 - email:
 - name:
### When (Command): None
### Then:
  * 'Confirmation Mails to send TODO' (SPEC_READMODEL)
Fields:
 - email:
 - notification sent:
 - user_id:
 - name:
# Spec End

# Spec Start
Title: spec: Confirmation Mails to send - scenario
### Given (Events):
  * 'Account created' (SPEC_EVENT)
Fields:
 - user id:
 - email: martin@nebulit.de
 - name:
### When (Command): None
### Then:
  * 'Confirmation Mails to send TODO' (SPEC_READMODEL)
Fields:
 - email: martin@nebulit.de
 - notification sent:
 - user_id: 9a2429f5-5ce5-4a0d-aad6-2be7617a4eeb
 - name:
# Spec End */
/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010052
*/
@Component
class ConfirmationMailsToSendTODOReadModelProjector(
    var repository: ConfirmationMailsToSendTODOReadModelRepository
) {

  @EventHandler
  fun on(event: CustomerNotifiedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.userId).orElse(ConfirmationMailsToSendTODOReadModelEntity())
    entity
        .apply {
          email = event.email
          userId = event.userId
          name = event.name
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: AccountCreatedEvent) {
    // throws exception if not available (adjust logic)
    val entity =
        this.repository.findById(event.userId).orElse(ConfirmationMailsToSendTODOReadModelEntity())
    entity
        .apply {
          email = event.email
          name = event.name
        }
        .also { this.repository.save(it) }
  }
}
