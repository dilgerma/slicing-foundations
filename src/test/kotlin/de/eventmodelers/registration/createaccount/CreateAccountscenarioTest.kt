package de.eventmodelers.registration.createaccount

import de.eventmodelers.common.CommandException
import de.eventmodelers.common.Event
import de.eventmodelers.common.support.RandomData
import de.eventmodelers.domain.RegistrationAggregate
import de.eventmodelers.events.AccountCreatedEvent
import de.eventmodelers.registration.domain.commands.createaccount.CreateAccountCommand
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655610377233 */
class CreateAccountscenarioTest {

  private lateinit var fixture: FixtureConfiguration<RegistrationAggregate>

  @BeforeEach
  fun setUp() {
    fixture = AggregateTestFixture(RegistrationAggregate::class.java)
  }

  @Test
  fun `Create Accountscenario Test`() {

    var aggregateId: java.util.UUID = RandomData.newInstance<java.util.UUID> {}

    // GIVEN
    val events = mutableListOf<Event>()

    events.add(
        RandomData.newInstance<AccountCreatedEvent> {
          this.userId = userId
          email = "martin@nebulit.de"
          name = RandomData.newInstance {}
        })

    // WHEN
    val command =
        CreateAccountCommand(
            userId = RandomData.newInstance {},
            email = "martin@nebulit.de",
            name = RandomData.newInstance {})

    // THEN
    val expectedEvents = mutableListOf<Event>()

    fixture.given(events).`when`(command).expectException(CommandException::class.java)
  }
}
