package de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.internal

import de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.BookDetailLookupForBorrowingDetailsReadModel
import de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.BookDetailLookupForBorrowingDetailsReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742569625
*/
@Component
class BookDetailLookupForBorrowingDetailsReadModelQueryHandler(
    private val repository: BookDetailLookupForBorrowingDetailsReadModelRepository
) {

  @QueryHandler
  fun handleQuery(
      query: BookDetailLookupForBorrowingDetailsReadModelQuery
  ): BookDetailLookupForBorrowingDetailsReadModel? {
    return BookDetailLookupForBorrowingDetailsReadModel(repository.findAll())
  }
}
