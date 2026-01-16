package de.eventmodelers.borrowing.useremaillookupforreservationnotification

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class UserEmailLookupForReservationNotificationReadModelQuery(val userId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711660
*/
@Entity
class UserEmailLookupForReservationNotificationReadModelEntity {
  @Id @Column(name = "user-id") var userId: String? = null

  @Column(name = "email") var email: String? = null

  @Column(name = "name") var name: String? = null
}

data class UserEmailLookupForReservationNotificationReadModel(
    val data: UserEmailLookupForReservationNotificationReadModelEntity
)
