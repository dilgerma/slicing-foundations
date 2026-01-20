package de.eventmodelers.domain

import de.eventmodelers.common.support.RandomData
import de.eventmodelers.domain.createcatalogentry.CreateCatalogEntryCommand
import de.eventmodelers.events.CatalogEntryCreatedEvent
import org.apache.zookeeper.cli.CreateCommand
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import javax.xml.catalog.Catalog

class CatalogManagementTest {

    @Test
    fun createCatalogEntry() {
        val fixture = AggregateTestFixture(CatalogManagement::class.java)

        //val command = RandomData.newInstance<CreateCatalogEntryCommand> {  }
        val command = CreateCatalogEntryCommand(
            "1",
            "Harry Potter",
            "J.K. Rowling",
            "my description",
            "1234"
        )
        fixture.givenNoPriorActivity().`when`(
            command
        )
            .expectSuccessfulHandlerExecution()
            .expectEvents(
            CatalogEntryCreatedEvent(
                itemId = command.itemId,
                title = command.title,
                author = command.author,
                description = command.description,
                isbn = command.isbn,
            )
        )

    }
}