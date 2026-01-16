package de.eventmodelers.borrowing.reservations

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass

class AllReservationsReadModelQuery()

data class ReservationsReadModelQuery(var bookId: String, var userId: String)

@Embeddable data class ReservationsReadModelKey(var bookId: String, var userId: String)

@IdClass(ReservationsReadModelKey::class)
@Entity
class ReservationsReadModelEntity {
  @Id @Column(name = "book-id") var bookId: String? = null

  @Id @Column(name = "user-id") var userId: String? = null
}

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711635
*/
data class ReservationsReadModel(val data: List<ReservationsReadModelEntity>)
