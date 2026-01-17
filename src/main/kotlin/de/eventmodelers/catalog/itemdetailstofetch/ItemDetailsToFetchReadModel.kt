package de.eventmodelers.catalog.itemdetailstofetch

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class ItemDetailsToFetchReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655839872567
*/
@Entity
class ItemDetailsToFetchReadModelEntity {
  @Column(name = "fetched") var fetched: Boolean = false
  @Id @Column(name = "itemId") lateinit var itemId: String
}

data class ItemDetailsToFetchReadModel(val data: List<ItemDetailsToFetchReadModelEntity>)
