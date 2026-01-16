package de.eventmodelers.catalog.itemdetailsforpublication

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class ItemDetailsForPublicationReadModelQuery(val itemId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655738119679
*/
@Entity
class ItemDetailsForPublicationReadModelEntity {
  @Column(name = "author") var author: String? = null

  @Column(name = "description") var description: String? = null

  @Id @Column(name = "item-id") var itemId: String? = null

  @Column(name = "title") var title: String? = null
}

data class ItemDetailsForPublicationReadModel(val data: ItemDetailsForPublicationReadModelEntity)
