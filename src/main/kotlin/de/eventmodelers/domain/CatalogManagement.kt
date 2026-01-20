package de.eventmodelers.domain

import de.eventmodelers.domain.createcatalogentry.CreateCatalogEntryCommand
import de.eventmodelers.events.CatalogEntryCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class CatalogManagement {

    @AggregateIdentifier
     var aggregateId: String? = null

    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    @CommandHandler
    fun handle(command: CreateCatalogEntryCommand) {

        // validate

        // apply events
        AggregateLifecycle.apply(
            CatalogEntryCreatedEvent(
                command.itemId, command.title, command.author, command.description, command.isbn
            )
        )
    }

    @EventSourcingHandler
    fun on(evt: CatalogEntryCreatedEvent) {
        this.aggregateId = evt.itemId
    }
}