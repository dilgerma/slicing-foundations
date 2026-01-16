package de.eventmodelers.catalog.catalogentries

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class CatalogEntriesReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655718555043
*/
@Entity
class CatalogEntriesReadModelEntity {
  @Column(name = "title") var title: String? = null

  @Id @Column(name = "item-id") var itemId: String? = null
}

data class CatalogEntriesReadModel(val data: List<CatalogEntriesReadModelEntity>)
