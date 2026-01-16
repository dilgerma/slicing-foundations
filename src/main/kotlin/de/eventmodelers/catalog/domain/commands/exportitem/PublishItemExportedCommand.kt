package de.eventmodelers.catalog.domain.commands.exportitem

import de.eventmodelers.common.Command

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655738191723
*/
data class PublishItemExportedCommand(
    var author: String,
    var description: String,
    var itemId: String,
    var title: String
) : Command
