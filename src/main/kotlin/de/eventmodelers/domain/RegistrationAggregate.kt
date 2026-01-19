package de.eventmodelers.domain

import de.eventmodelers.events.AccountConfirmedEvent
import de.eventmodelers.events.AccountCreatedEvent
import de.eventmodelers.events.AccountDeactivatedEvent
import de.eventmodelers.events.AccountUpdatedEvent
import de.eventmodelers.events.CustomerNotifiedEvent
import de.eventmodelers.registration.domain.commands.confirmaccount.ConfirmAccountCommand
import de.eventmodelers.registration.domain.commands.createaccount.CreateAccountCommand
import de.eventmodelers.registration.domain.commands.deactivateaccount.DeactivateAccountCommand
import de.eventmodelers.registration.domain.commands.notifycustomer.NotifyCustomerCommand
import de.eventmodelers.registration.domain.commands.updateaccount.UpdateAccountCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class RegistrationAggregate {

  @AggregateIdentifier var userId: String? = null

  /*

  # Spec Start
  Title: spec: Create Account - scenario
  ### Given (Events):
  * 'Account created' (SPEC_EVENT)
  Fields:
  - user id:
  - email: martin@nebulit.de
  - name:
  ### When (Command):
  * 'Create Account' (SPEC_COMMAND)
  Fields:
  - user_id:
  - email: martin@nebulit.de
  - name:
  ### Then:
  * 'emails can only be registered once' (SPEC_ERROR)
  # Spec End
  */

  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: CreateAccountCommand) {

    AggregateLifecycle.apply(
        AccountCreatedEvent(email = command.email, name = command.name, userId = command.userId))
  }

  @EventSourcingHandler
  fun on(event: AccountCreatedEvent) {
    // handle event
    userId = event.userId
  }

  @CommandHandler
  fun handle(command: NotifyCustomerCommand) {

    AggregateLifecycle.apply(
        CustomerNotifiedEvent(
            email = command.email,
            name = command.name,
            userId = command.userId,
            token = command.token))
  }

  @EventSourcingHandler
  fun on(event: CustomerNotifiedEvent) {
    // handle event

  }

  /*
  # Spec Start
  Title: spec: Confirm Account - scenario
  ### Given (Events):
  * 'Customer notified' (SPEC_EVENT)
  Fields:
  - user_id:
  - email: martin@nebulit.de
  - name:
  - token: 1234
  * 'Account created' (SPEC_EVENT)
  Fields:
  - user id:
  - email: martin@nebulit.de
  - name:
  ### When (Command):
  * 'Confirm Account' (SPEC_COMMAND)
  Fields:
  - email: martin@nebulit.de
  - user_id:
  - token: 1234
  ### Then:
  * 'Account confirmed' (SPEC_EVENT)
  Fields:
  - user_id:
  - email: martin@nebulit.de
  # Spec End
  */

  @CommandHandler
  fun handle(command: ConfirmAccountCommand) {

    AggregateLifecycle.apply(AccountConfirmedEvent(email = command.email, userId = command.user_id))
  }

  @EventSourcingHandler
  fun on(event: AccountConfirmedEvent) {
    // handle event

  }

  @CommandHandler
  fun handle(command: UpdateAccountCommand) {

    AggregateLifecycle.apply(
        AccountUpdatedEvent(name = command.name, email = command.email, user_id = command.user_id))
  }

  @EventSourcingHandler
  fun on(event: AccountUpdatedEvent) {
    // handle event

  }

  @CommandHandler
  fun handle(command: DeactivateAccountCommand) {

    AggregateLifecycle.apply(AccountDeactivatedEvent(user_id = command.user_id))
  }

  @EventSourcingHandler
  fun on(event: AccountDeactivatedEvent) {
    // handle event

  }
}
