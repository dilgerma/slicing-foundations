package de.eventmodelers.registration.notifycustomer.internal

import de.eventmodelers.common.Processor
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655391010164
*/
@Component
class SendNotificationAutomationProcessor : Processor {
  var logger = KotlinLogging.logger {}

  @Autowired lateinit var commandGateway: CommandGateway
  @Autowired lateinit var queryGateway: QueryGateway

  // TODO ass missing implementation
}
