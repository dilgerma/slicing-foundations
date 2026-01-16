package de.eventmodelers.registration.confirmaccount.integration

import de.eventmodelers.common.support.BaseIntegrationTest
import de.eventmodelers.common.support.RandomData
import de.eventmodelers.common.support.StreamAssertions
import de.eventmodelers.common.support.awaitUntilAssserted
import de.eventmodelers.events.AccountConfirmedEvent
import de.eventmodelers.registration.domain.commands.createaccount.CreateAccountCommand
import de.eventmodelers.registration.domain.commands.notifycustomer.NotifyCustomerCommand
import java.util.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/** Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655611113351 */
class ConfirmAccountscenarioTest : BaseIntegrationTest() {

  @Autowired private lateinit var commandGateway: CommandGateway

  @Autowired private lateinit var streamAssertions: StreamAssertions

  @Test
  fun `Confirm Accountscenario Test`() {

    val userId = UUID.randomUUID().toString()

    var createAccountCommand = RandomData.newInstance<CreateAccountCommand> { this.userId = userId }

    commandGateway.sendAndWait<Any>(createAccountCommand)

    var notifyCustomerCommand =
        RandomData.newInstance<NotifyCustomerCommand> { this.userId = userId }

    commandGateway.sendAndWait<Any>(notifyCustomerCommand)

    awaitUntilAssserted { streamAssertions.assertEvent(userId) { it is AccountConfirmedEvent } }
  }
}
