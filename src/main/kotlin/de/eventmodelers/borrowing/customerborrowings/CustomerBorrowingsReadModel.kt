package de.eventmodelers.borrowing.customerborrowings

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class CustomerBorrowingsReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711650
*/
@Entity
class CustomerBorrowingsReadModelEntity {
  @Id @Column(name = "reservation-id") var reservationId: String? = null

  @Column(name = "user-id") var userId: String? = null
}

data class CustomerBorrowingsReadModel(val data: List<CustomerBorrowingsReadModelEntity>)
