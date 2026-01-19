package de.eventmodelers.catalog.itemdetailstofetch.integration

import de.eventmodelers.catalog.domain.commands.addmissingdata.AddMissingDataCommand
import de.eventmodelers.catalog.domain.commands.createcatalogentry.CreateCatalogEntryCommand
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.ItemDetailsToFetchReadModelQuery
import de.eventmodelers.common.support.BaseIntegrationTest
import de.eventmodelers.common.support.RandomData
import de.eventmodelers.common.support.awaitUntilAssserted
import de.eventmodelers.support.metadata.SESSION_ID_HEADER
import java.util.*
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.MetaData
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/** Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655840053326 */
class ItemDetailstofetchisemptywhenfetchedReadModelTest : BaseIntegrationTest() {

  @Autowired private lateinit var commandGateway: CommandGateway

  @Autowired private lateinit var queryGateway: QueryGateway

  @Test
  fun `Item Detailstofetchisemptywhenfetched Read Model Test`() {

    val itemId = RandomData.newInstance<String> {}

    var createCatalogEntryCommand =
        RandomData.newInstance<CreateCatalogEntryCommand> { this.itemId = itemId }

    commandGateway.sendAndWait<Any>(
        createCatalogEntryCommand, MetaData.with(SESSION_ID_HEADER, "1234"))

    var addMissingDataCommand =
        RandomData.newInstance<AddMissingDataCommand> { this.itemId = itemId }

    commandGateway.sendAndWait<Any>(addMissingDataCommand, MetaData.with(SESSION_ID_HEADER, "1234"))

    awaitUntilAssserted {
      var readModel =
          queryGateway.query(
              ItemDetailsToFetchReadModelQuery(), ItemDetailsToFetchReadModel::class.java)
      // TODO add assertions
      assertThat(readModel.get().data).isEmpty()
    }
  }
}
