package de.eventmodelers.borrowing.reservationlookupforborrowingdetails

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class ReservationLookupForBorrowingDetailsReadModelQuery(val reservationId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742424589
*/
@Entity
class ReservationLookupForBorrowingDetailsReadModelEntity {
  @Column(name = "book-id") var bookId: String? = null

  @Id @Column(name = "reservation-id") var reservationId: String? = null

  @Column(name = "user-id") var userId: String? = null
}

data class ReservationLookupForBorrowingDetailsReadModel(
    val data: ReservationLookupForBorrowingDetailsReadModelEntity
)
