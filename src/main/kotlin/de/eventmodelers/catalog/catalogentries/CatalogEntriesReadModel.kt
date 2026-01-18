package de.eventmodelers.catalog.catalogentries

import de.eventmodelers.common.ReadModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime
import org.springframework.format.annotation.DateTimeFormat

@Table(name = "catalog_entries_read_model_entity", schema = "public")
@Entity
class CatalogEntriesReadModelEntity {
    @Id
    @Column(name = "item_id")
    var itemId: String? = null

    @Column(name = "title")
    var title: String? = null
}

data class CatalogEntriesReadModel(val data: List<CatalogEntriesReadModelEntity>) : ReadModel
