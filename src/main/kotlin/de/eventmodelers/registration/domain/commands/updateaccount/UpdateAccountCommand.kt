package de.eventmodelers.registration.domain.commands.updateaccount

import de.eventmodelers.common.Command

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392353799
*/
data class UpdateAccountCommand(var name: String, var email: String, var user_id: String) : Command
