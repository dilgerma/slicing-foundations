package de.eventmodelers.registration.confirmaccount

import de.eventmodelers.common.Event
import de.eventmodelers.common.support.RandomData
import de.eventmodelers.domain.RegistrationAggregate
import de.eventmodelers.events.AccountConfirmedEvent
import de.eventmodelers.events.AccountCreatedEvent
import de.eventmodelers.events.CustomerNotifiedEvent
import de.eventmodelers.registration.domain.commands.confirmaccount.ConfirmAccountCommand
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655611113351 */
class ConfirmAccountscenarioTest {

  private lateinit var fixture: FixtureConfiguration<RegistrationAggregate>

  @BeforeEach
  fun setUp() {
    fixture = AggregateTestFixture(RegistrationAggregate::class.java)
  }

  @Test
  fun `Confirm Accountscenario Test`() {

    var aggregateId: java.util.UUID = RandomData.newInstance<java.util.UUID> {}

    // GIVEN
    val events = mutableListOf<Event>()

    events.add(
        RandomData.newInstance<AccountCreatedEvent> {
          this.userId = userId
          email = "martin@nebulit.de"
          name = RandomData.newInstance {}
        })
    events.add(
        RandomData.newInstance<CustomerNotifiedEvent> {
          this.userId = userId
          email = "martin@nebulit.de"
          name = RandomData.newInstance {}
          token = "1234"
        })

    // WHEN
    val command =
        ConfirmAccountCommand(
            email = "martin@nebulit.de", user_id = RandomData.newInstance {}, token = "1234")

    // THEN
    val expectedEvents = mutableListOf<Event>()

    expectedEvents.add(
        RandomData.newInstance<AccountConfirmedEvent> {
          this.userId = command.user_id
          this.email = "martin@nebulit.de"
        })

    fixture
        .given(events)
        .`when`(command)
        .expectSuccessfulHandlerExecution()
        .expectEvents(*expectedEvents.toTypedArray())
  }
}
