package de.eventmodelers.borrowing.useremaillookupforreservationnotification.internal

import de.eventmodelers.borrowing.useremaillookupforreservationnotification.UserEmailLookupForReservationNotificationReadModelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface UserEmailLookupForReservationNotificationReadModelRepository :
    JpaRepository<UserEmailLookupForReservationNotificationReadModelEntity, String>

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655729711660
*/
@Component
class UserEmailLookupForReservationNotificationReadModelProjector(
    var repository: UserEmailLookupForReservationNotificationReadModelRepository
) {}
