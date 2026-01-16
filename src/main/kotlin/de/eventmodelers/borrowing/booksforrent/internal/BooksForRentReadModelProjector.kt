package de.eventmodelers.borrowing.booksforrent.internal

import de.eventmodelers.borrowing.booksforrent.BooksForRentReadModelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface BooksForRentReadModelRepository : JpaRepository<BooksForRentReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711659
*/
@Component class BooksForRentReadModelProjector(var repository: BooksForRentReadModelRepository) {}
