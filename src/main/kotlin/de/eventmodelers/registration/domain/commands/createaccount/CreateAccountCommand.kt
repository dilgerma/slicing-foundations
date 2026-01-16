package de.eventmodelers.registration.domain.commands.createaccount

import de.eventmodelers.common.Command

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655390776063
*/
data class CreateAccountCommand(var userId: String, var email: String, var name: String) : Command
