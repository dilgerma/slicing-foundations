package de.eventmodelers.catalog.createcatalogentry

import de.eventmodelers.catalog.domain.commands.createcatalogentry.CreateCatalogEntryCommand
import de.eventmodelers.common.Event
import de.eventmodelers.common.support.RandomData
import de.eventmodelers.domain.CatalogueManagementAggregate
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import java.time.LocalDateTime
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655826436475 */
class CreateCatalogEntryTest {

  private lateinit var fixture: FixtureConfiguration<CatalogueManagementAggregate>

  @BeforeEach
  fun setUp() {
    fixture = AggregateTestFixture(CatalogueManagementAggregate::class.java)
  }

  @Test
  fun `Create Catalog Entry Test`() {

    // GIVEN
    val events = mutableListOf<Event>()

    // WHEN
    val command =
        CreateCatalogEntryCommand(
            itemId = RandomData.newInstance {},
            title = RandomData.newInstance {},
            author = RandomData.newInstance {},
            description = RandomData.newInstance {},
            isbn = RandomData.newInstance {},
            createdDate = LocalDateTime.now())

    // THEN
    val expectedEvents = mutableListOf<Event>()

    expectedEvents.add(
        RandomData.newInstance<CatalogueEntryCreatedEvent> {
          this.author = command.author
          this.description = command.description
          this.itemId = command.itemId
          this.title = command.title
          this.isbn = command.isbn
          createdDate = command.createdDate
        })

    fixture
        .given(events)
        .`when`(command)
        .expectSuccessfulHandlerExecution()
        .expectEvents(*expectedEvents.toTypedArray())
  }
}
