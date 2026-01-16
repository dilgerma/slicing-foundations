package de.eventmodelers.borrowing.domain.commands.expirereservation

import de.eventmodelers.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711644
*/
data class ExpireReservationCommand(@TargetAggregateIdentifier var reservationId: String) : Command
