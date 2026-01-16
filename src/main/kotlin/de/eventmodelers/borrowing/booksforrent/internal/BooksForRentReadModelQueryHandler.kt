package de.eventmodelers.borrowing.booksforrent.internal

import de.eventmodelers.borrowing.booksforrent.BooksForRentReadModel
import de.eventmodelers.borrowing.booksforrent.BooksForRentReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711659
*/
@Component
class BooksForRentReadModelQueryHandler(private val repository: BooksForRentReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: BooksForRentReadModelQuery): BooksForRentReadModel? {

    if (!repository.existsById(query.bookId)) {
      return null
    }
    return BooksForRentReadModel(repository.findById(query.bookId).get())
  }
}
