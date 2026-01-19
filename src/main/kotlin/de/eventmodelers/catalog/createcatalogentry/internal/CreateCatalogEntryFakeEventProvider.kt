package de.eventmodelers.catalog.createcatalogentry.internal

import de.eventmodelers.catalog.domain.commands.createcatalogentry.CreateCatalogEntryCommand
import java.util.UUID
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@ConditionalOnProperty(name = ["createcatalogentry.fakeevents.enabled"], havingValue = "true")
@Component
class CreateCatalogEntryFakeEventProvider(val commandGateway: CommandGateway) :
    ApplicationListener<ApplicationStartedEvent> {
  override fun onApplicationEvent(event: ApplicationStartedEvent) {
    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "Harry Potter",
            "J.K. Rowling",
            "A young wizard discovers his destiny",
            "978-0747532743"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "The Lord of the Rings",
            "J.R.R. Tolkien",
            "An epic high-fantasy novel set in Middle-earth",
            "978-0618640157"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "1984",
            "George Orwell",
            "A dystopian novel about surveillance and totalitarianism",
            "978-0451524935"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "Brave New World",
            "Aldous Huxley",
            "A futuristic society driven by technology and control",
            "978-0060850524"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "Fahrenheit 451",
            "Ray Bradbury",
            "A society where books are banned and burned",
            "978-1451673319"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "The Hitchhiker's Guide to the Galaxy",
            "Douglas Adams",
            "A humorous science fiction adventure through space",
            "978-0345391803"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "Dune",
            "Frank Herbert",
            "A science fiction saga about power, politics, and desert planets",
            "978-0441172719"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "To Kill a Mockingbird",
            "Harper Lee",
            "A novel about justice and morality in the American South",
            "978-0060935467"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "The Pragmatic Programmer",
            "Andrew Hunt & David Thomas",
            "A practical guide to software craftsmanship",
            "978-0135957059"))

    commandGateway.send<Any>(
        CreateCatalogEntryCommand(
            UUID.randomUUID().toString(),
            "Clean Architecture",
            "Robert C. Martin",
            "Principles and practices for building maintainable software systems",
            "978-0134494166"))
  }
}
