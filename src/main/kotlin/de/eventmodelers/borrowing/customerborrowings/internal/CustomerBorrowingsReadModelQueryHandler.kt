package de.eventmodelers.borrowing.customerborrowings.internal

import de.eventmodelers.borrowing.customerborrowings.CustomerBorrowingsReadModel
import de.eventmodelers.borrowing.customerborrowings.CustomerBorrowingsReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711650
*/
@Component
class CustomerBorrowingsReadModelQueryHandler(
    private val repository: CustomerBorrowingsReadModelRepository
) {

  @QueryHandler
  fun handleQuery(query: CustomerBorrowingsReadModelQuery): CustomerBorrowingsReadModel? {
    return CustomerBorrowingsReadModel(repository.findAll())
  }
}
