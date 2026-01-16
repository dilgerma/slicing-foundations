package de.eventmodelers.borrowing.activereservations

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class ActiveReservationsReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730201944
*/
@Entity
class ActiveReservationsReadModelEntity {
  @Column(name = "book-id") var bookId: String? = null

  @Id @Column(name = "reservation-id") var reservationId: String? = null

  @Column(name = "user-id") var userId: String? = null
}

data class ActiveReservationsReadModel(val data: List<ActiveReservationsReadModelEntity>)
