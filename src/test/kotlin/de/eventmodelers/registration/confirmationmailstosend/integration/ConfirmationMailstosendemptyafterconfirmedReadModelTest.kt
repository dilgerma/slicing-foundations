package de.eventmodelers.registration.confirmationmailstosend.integration

import de.eventmodelers.common.support.BaseIntegrationTest
import de.eventmodelers.common.support.RandomData
import de.eventmodelers.common.support.awaitUntilAssserted
import de.eventmodelers.registration.confirmationmailstosend.ConfirmationMailsToSendTODOReadModel
import de.eventmodelers.registration.confirmationmailstosend.ConfirmationMailsToSendTODOReadModelQuery
import de.eventmodelers.registration.domain.commands.createaccount.CreateAccountCommand
import de.eventmodelers.registration.domain.commands.notifycustomer.NotifyCustomerCommand
import java.util.*
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/** Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655610591882 */
class ConfirmationMailstosendemptyafterconfirmedReadModelTest : BaseIntegrationTest() {

  @Autowired private lateinit var commandGateway: CommandGateway

  @Autowired private lateinit var queryGateway: QueryGateway

  @Test
  fun `Confirmation Mailstosendemptyafterconfirmed Read Model Test`() {

    val userId = UUID.randomUUID().toString()

    var createAccountCommand = RandomData.newInstance<CreateAccountCommand> { this.userId = userId }

    commandGateway.sendAndWait<Any>(createAccountCommand)

    var notifyCustomerCommand =
        RandomData.newInstance<NotifyCustomerCommand> { this.userId = userId }

    commandGateway.sendAndWait<Any>(notifyCustomerCommand)

    awaitUntilAssserted {
      var readModel =
          queryGateway.query(
              ConfirmationMailsToSendTODOReadModelQuery(),
              ConfirmationMailsToSendTODOReadModel::class.java)
      assertThat(readModel.get().data).isNotEmpty
    }
  }
}
