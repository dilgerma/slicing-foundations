package de.eventmodelers.catalog.itemstopublish

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class ItemsToPublishReadModelQuery(val itemId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655736566009
*/
@Entity
class ItemsToPublishReadModelEntity {
  @Id @Column(name = "item-id") var itemId: String? = null

  @Column(name = "archived") var archived: Boolean? = null
}

data class ItemsToPublishReadModel(val data: ItemsToPublishReadModelEntity)
