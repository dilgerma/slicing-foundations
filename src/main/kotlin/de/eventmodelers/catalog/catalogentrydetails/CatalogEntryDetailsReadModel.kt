package de.eventmodelers.catalog.catalogentrydetails

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class CatalogEntryDetailsReadModelQuery(val itemId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655723786459
*/
@Entity
class CatalogEntryDetailsReadModelEntity {
  @Column(name = "author") var author: String? = null

  @Column(name = "description") var description: String? = null

  @Id @Column(name = "item-id") var itemId: String? = null

  @Column(name = "title") var title: String? = null
}

data class CatalogEntryDetailsReadModel(val data: CatalogEntryDetailsReadModelEntity)
