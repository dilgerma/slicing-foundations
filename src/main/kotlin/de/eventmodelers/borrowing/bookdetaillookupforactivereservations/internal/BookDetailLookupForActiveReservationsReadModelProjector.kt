package de.eventmodelers.borrowing.bookdetaillookupforactivereservations.internal

import de.eventmodelers.borrowing.bookdetaillookupforactivereservations.BookDetailLookupForActiveReservationsReadModelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface BookDetailLookupForActiveReservationsReadModelRepository :
    JpaRepository<BookDetailLookupForActiveReservationsReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655730751939
*/
@Component
class BookDetailLookupForActiveReservationsReadModelProjector(
    var repository: BookDetailLookupForActiveReservationsReadModelRepository
) {}
