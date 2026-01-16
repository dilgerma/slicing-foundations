package de.eventmodelers.registration.domain.commands.deactivateaccount

import de.eventmodelers.common.Command

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655393190353
*/
data class DeactivateAccountCommand(var user_id: String) : Command
