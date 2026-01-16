package de.eventmodelers.borrowing.reservationnotificationtosend

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class ReservationNotificationToSendReadModelQuery(val reservationId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711638
*/
@Entity
class ReservationNotificationToSendReadModelEntity {
  @Column(name = "book-id") var bookId: String? = null

  @Column(name = "user-id") var userId: String? = null

  @Id @Column(name = "reservation-id") var reservationId: String? = null
}

data class ReservationNotificationToSendReadModel(
    val data: ReservationNotificationToSendReadModelEntity
)
