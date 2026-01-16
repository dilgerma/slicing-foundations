package de.eventmodelers.borrowing.reservationsforexpiration

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

data class ReservationsForExpirationReadModelQuery(val reservationId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711645
*/
@Entity
class ReservationsForExpirationReadModelEntity {
  @Column(name = "book-id") var bookId: String? = null

  @Id @Column(name = "reservation-id") var reservationId: String? = null

  @Column(name = "user-id") var userId: String? = null

  @Column(name = "borrowDate") var borrowDate: LocalDateTime? = null
}

data class ReservationsForExpirationReadModel(val data: ReservationsForExpirationReadModelEntity)
