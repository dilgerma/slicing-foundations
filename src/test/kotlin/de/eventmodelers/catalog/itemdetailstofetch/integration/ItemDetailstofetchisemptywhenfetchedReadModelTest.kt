package de.eventmodelers.catalog.itemdetailstofetch.integration

import de.eventmodelers.catalog.domain.commands.addmissingdata.AddDataFromBookSystemCommand
import de.eventmodelers.catalog.domain.commands.createcatalogentry.CreateCatalogEntryCommand
import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModel
import de.eventmodelers.catalog.itemdetailstofetch.CatalogEntryDetailsToFetchReadModelQuery
import de.eventmodelers.common.support.BaseIntegrationTest
import de.eventmodelers.common.support.RandomData
import de.eventmodelers.common.support.awaitUntilAssserted
import org.assertj.core.api.Assertions.assertThat
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/**


Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115516
 */
class ItemDetailstofetchisemptywhenfetchedReadModelTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var commandGateway: CommandGateway

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @Test
    fun `Item Detailstofetchisemptywhenfetched Read Model Test`() {

        val itemId = RandomData.newInstance<String> {}


        var createCatalogEntryCommand = RandomData.newInstance<CreateCatalogEntryCommand> {
            this.itemId = itemId
        }

        commandGateway.sendAndWait<Any>(createCatalogEntryCommand)


        var addDataFromBookSystemCommand = RandomData.newInstance<AddDataFromBookSystemCommand> {
            this.itemId = itemId
        }

        commandGateway.sendAndWait<Any>(addDataFromBookSystemCommand)


        awaitUntilAssserted {
            var readModel = queryGateway.query(
                CatalogEntryDetailsToFetchReadModelQuery(),
                CatalogEntryDetailsToFetchReadModel::class.java
            )
            //TODO add assertions
            assertThat(readModel.get().data).isEmpty()
        }


    }

}
