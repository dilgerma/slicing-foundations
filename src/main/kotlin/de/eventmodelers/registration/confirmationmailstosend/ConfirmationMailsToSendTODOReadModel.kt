package de.eventmodelers.registration.confirmationmailstosend

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class ConfirmationMailsToSendTODOReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010052
*/
@Entity
class ConfirmationMailsToSendTODOReadModelEntity {
  @Id @Column(name = "email") var email: String? = null

  @Column(name = "notification-sent") var notificationSent: String? = null

  @Column(name = "user_id") var userId: String? = null

  @Column(name = "name") var name: String? = null
}

data class ConfirmationMailsToSendTODOReadModel(
    val data: List<ConfirmationMailsToSendTODOReadModelEntity>
)
