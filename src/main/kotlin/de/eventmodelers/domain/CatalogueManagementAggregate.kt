package de.eventmodelers.domain

import de.eventmodelers.catalog.domain.commands.addmissingdata.AddMissingDataCommand
import de.eventmodelers.catalog.domain.commands.createcatalogentry.CreateCatalogEntryCommand
import de.eventmodelers.events.CatalogueEntryCreatedEvent
import de.eventmodelers.events.ItemInformationAddedEvent
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

  /*
  //AI-TODO:

  # Spec Start
  Title: spec: Create Catalog Entry
  ### Given (Events): None
  ### When (Command):
  * 'Create Catalog Entry' (SPEC_COMMAND)
  Fields:
  - itemId:
  - title:
  - author:
  - description:
  ### Then:
  * 'Catalogue entry created' (SPEC_EVENT)
  Fields:
  - author:
  - description:
  - itemId:
  - title:
  # Spec End
  */

  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: CreateCatalogEntryCommand) {

    AggregateLifecycle.apply(
        CatalogueEntryCreatedEvent(
            itemId = command.itemId,
            title = command.title,
            author = command.author,
            description = command.description,
            isbn = command.isbn,
            createdDate = command.createdDate))
  }

  @EventSourcingHandler
  fun on(event: CatalogueEntryCreatedEvent) {
    // handle event
    this.itemId = event.itemId
  }

  @CommandHandler
  fun handle(command: AddMissingDataCommand) {
    AggregateLifecycle.apply(
        ItemInformationAddedEvent(
            command.itemId, command.title, command.author, command.description))
  }
}
