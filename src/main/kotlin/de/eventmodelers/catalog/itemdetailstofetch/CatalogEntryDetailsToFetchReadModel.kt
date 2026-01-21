package de.eventmodelers.catalog.itemdetailstofetch

import jakarta.persistence.ElementCollection
import jakarta.persistence.CollectionTable
import jakarta.persistence.JoinColumn
import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType

import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types


class CatalogEntryDetailsToFetchReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115509
*/
@Entity
class CatalogEntryDetailsToFetchReadModelEntity {
    @Column(name = "fetched")
    var fetched: Boolean? = null;
    @Id
    @Column(name = "itemId")
    var itemId: String? = null;
    @Column(name = "isbn")
    var isbn: String? = null;
}

data class CatalogEntryDetailsToFetchReadModel(val data: List<CatalogEntryDetailsToFetchReadModelEntity>)
