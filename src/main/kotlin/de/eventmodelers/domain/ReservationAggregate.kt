package de.eventmodelers.domain

import de.eventmodelers.borrowing.domain.commands.expirereservation.ExpireReservationCommand
import de.eventmodelers.borrowing.domain.commands.markdamaged.MarkDamagedCommand
import de.eventmodelers.borrowing.domain.commands.marklost.MarkLostCommand
import de.eventmodelers.borrowing.domain.commands.markreservationaspickedup.MarkReservationAsPickedUpCommand
import de.eventmodelers.borrowing.domain.commands.reservebook.ReserveBookCommand
import de.eventmodelers.borrowing.domain.commands.sendreservationnotification.SendReservationNotificationCommand
import de.eventmodelers.events.BookMarkedLostEvent
import de.eventmodelers.events.BookReservedEvent
import de.eventmodelers.events.MarkDamagedEvent
import de.eventmodelers.events.ReservationExpiredEvent
import de.eventmodelers.events.ReservationMarkedAsPickedUpEvent
import de.eventmodelers.events.ReservationNotificationSentEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class ReservationAggregate {

  @AggregateIdentifier var reservationId: String? = null

  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: ReserveBookCommand) {

    AggregateLifecycle.apply(
        BookReservedEvent(
            bookId = command.bookId,
            userId = command.userId,
            reservationId = command.reservationId))
  }

  @EventSourcingHandler
  fun on(event: BookReservedEvent) {
    // handle event
    reservationId = event.reservationId
  }

  @CommandHandler
  fun handle(command: SendReservationNotificationCommand) {

    AggregateLifecycle.apply(
        ReservationNotificationSentEvent(
            email = command.email,
            name = command.name,
            userId = command.userId,
            reservationId = command.reservationId))
  }

  @EventSourcingHandler
  fun on(event: ReservationNotificationSentEvent) {
    // handle event
    reservationId = event.reservationId
  }

  @CommandHandler
  fun handle(command: ExpireReservationCommand) {

    AggregateLifecycle.apply(ReservationExpiredEvent(reservationId = command.reservationId))
  }

  @EventSourcingHandler
  fun on(event: ReservationExpiredEvent) {
    // handle event
    reservationId = event.reservationId
  }

  @CommandHandler
  fun handle(command: MarkReservationAsPickedUpCommand) {

    AggregateLifecycle.apply(
        ReservationMarkedAsPickedUpEvent(
            reservationId = command.reservationId, userId = command.userId))
  }

  @EventSourcingHandler
  fun on(event: ReservationMarkedAsPickedUpEvent) {
    // handle event
    reservationId = event.reservationId
  }

  @CommandHandler
  fun handle(command: MarkLostCommand) {

    AggregateLifecycle.apply(
        BookMarkedLostEvent(
            userId = command.userId,
            bookId = command.bookId,
            reservationId = command.reservationId))
  }

  @EventSourcingHandler
  fun on(event: BookMarkedLostEvent) {
    // handle event
    reservationId = event.reservationId
  }

  @CommandHandler
  fun handle(command: MarkDamagedCommand) {

    AggregateLifecycle.apply(
        MarkDamagedEvent(
            bookId = command.bookId,
            reservationId = command.reservationId,
            userId = command.userId))
  }

  @EventSourcingHandler
  fun on(event: MarkDamagedEvent) {
    // handle event
    reservationId = event.reservationId
  }
}
