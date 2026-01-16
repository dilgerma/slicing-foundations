package de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class BookDetailLookupForBorrowingDetailsReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742569625
*/
@Entity
class BookDetailLookupForBorrowingDetailsReadModelEntity {
  @Id @Column(name = "book-id") var bookId: String? = null

  @Column(name = "title") var title: String? = null

  @Column(name = "description") var description: String? = null
}

data class BookDetailLookupForBorrowingDetailsReadModel(
    val data: List<BookDetailLookupForBorrowingDetailsReadModelEntity>
)
