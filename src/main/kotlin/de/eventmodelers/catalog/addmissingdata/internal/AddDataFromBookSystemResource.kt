package de.eventmodelers.catalog.addmissingdata.internal

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RestController
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import de.eventmodelers.catalog.domain.commands.addmissingdata.AddDataFromBookSystemCommand


import java.util.concurrent.CompletableFuture


data class AddMissingDataPayload(	var itemId:String,
	var title:String,
	var author:String,
	var description:String,
	var isbn:String)

/*
Boardlink: https://miro.com/app/board/uXjVLmFhiDA=/?moveToWidget=3458764656044115512
*/
@RestController
class AddDataFromBookSystemResource(private var commandGateway: CommandGateway) {

    var logger = KotlinLogging.logger {}

    
    @CrossOrigin
    @PostMapping("/debug/addmissingdata")
    fun processDebugCommand(@RequestParam itemId:String,
	@RequestParam title:String,
	@RequestParam author:String,
	@RequestParam description:String,
	@RequestParam isbn:String):CompletableFuture<Any> {
        return commandGateway.send(AddDataFromBookSystemCommand(itemId,
	title,
	author,
	description,
	isbn))
    }
    

    
       @CrossOrigin
       @PostMapping("/addmissingdata/{id}")
    fun processCommand(
        @PathVariable("id") itemId: String,
        @RequestBody payload: AddMissingDataPayload
    ):CompletableFuture<Any> {
         return commandGateway.send(AddDataFromBookSystemCommand(			itemId=payload.itemId,
			title=payload.title,
			author=payload.author,
			description=payload.description,
			isbn=payload.isbn))
        }
       

}
