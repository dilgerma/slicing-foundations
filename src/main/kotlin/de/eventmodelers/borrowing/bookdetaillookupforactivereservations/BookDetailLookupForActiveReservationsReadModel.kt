package de.eventmodelers.borrowing.bookdetaillookupforactivereservations

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class BookDetailLookupForActiveReservationsReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730751939
*/
@Entity
class BookDetailLookupForActiveReservationsReadModelEntity {
  @Id @Column(name = "book-id") var bookId: String? = null

  @Column(name = "title") var title: String? = null

  @Column(name = "description") var description: String? = null
}

data class BookDetailLookupForActiveReservationsReadModel(
    val data: List<BookDetailLookupForActiveReservationsReadModelEntity>
)
