package de.eventmodelers.borrowing.booksforrent

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class BooksForRentReadModelQuery(val bookId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711659
*/
@Entity
class BooksForRentReadModelEntity {
  @Id @Column(name = "book-id") var bookId: String? = null

  @Column(name = "title") var title: String? = null

  @Column(name = "description") var description: String? = null
}

data class BooksForRentReadModel(val data: BooksForRentReadModelEntity)
