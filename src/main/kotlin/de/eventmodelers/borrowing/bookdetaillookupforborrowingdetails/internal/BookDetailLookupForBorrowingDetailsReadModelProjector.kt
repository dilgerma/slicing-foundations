package de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.internal

import de.eventmodelers.borrowing.bookdetaillookupforborrowingdetails.BookDetailLookupForBorrowingDetailsReadModelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface BookDetailLookupForBorrowingDetailsReadModelRepository :
    JpaRepository<BookDetailLookupForBorrowingDetailsReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655742569625
*/
@Component
class BookDetailLookupForBorrowingDetailsReadModelProjector(
    var repository: BookDetailLookupForBorrowingDetailsReadModelRepository
) {}
