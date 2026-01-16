package de.eventmodelers.domain

import de.eventmodelers.catalog.domain.commands.archivecatalogentry.ArchiveCatalogEntryCommand
import de.eventmodelers.catalog.domain.commands.createcatalogentry.CreateCatalogEntryCommand
import de.eventmodelers.catalog.domain.commands.publishitem.PublishItemCommand
import de.eventmodelers.catalog.domain.commands.updatecatalogentry.UpdateCatalogEntryCommand
import de.eventmodelers.events.CatalogueEntryArchivedEvent
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import de.eventmodelers.events.CatalogueEntryUpdatedEvent
import de.eventmodelers.events.ItemPublishedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class CatalogueManagementAggregate {

  @AggregateIdentifier var itemId: String? = null

    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: CreateCatalogEntryCommand) {

    AggregateLifecycle.apply(
        CatalogueEntryCreatedEvent(
            itemId = command.itemId,
            title = command.title,
            author = command.author,
            description = command.description))
  }

  @EventSourcingHandler
  fun on(event: CatalogueEntryCreatedEvent) {
    // handle event
    itemId = event.itemId
  }

  @CommandHandler
  fun handle(command: UpdateCatalogEntryCommand) {

    AggregateLifecycle.apply(
        CatalogueEntryUpdatedEvent(
            author = command.author,
            description = command.description,
            itemId = command.itemId,
            title = command.title))
  }

  @EventSourcingHandler
  fun on(event: CatalogueEntryUpdatedEvent) {
    // handle event
    itemId = event.itemId
  }

  @CommandHandler
  fun handle(command: ArchiveCatalogEntryCommand) {

    AggregateLifecycle.apply(CatalogueEntryArchivedEvent(itemId = command.itemId))
  }

  @EventSourcingHandler
  fun on(event: CatalogueEntryArchivedEvent) {
    // handle event
    itemId = event.itemId
  }

  @CommandHandler
  fun handle(command: PublishItemCommand) {

    AggregateLifecycle.apply(ItemPublishedEvent(itemId = command.itemId))
  }

  @EventSourcingHandler
  fun on(event: ItemPublishedEvent) {
    // handle event
    itemId = event.itemId
  }
}
