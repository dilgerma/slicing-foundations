package de.eventmodelers.registration.confirmedaccounts

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

class ConfirmedAccountsReadModelQuery()

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655392353436
*/
@Entity
class ConfirmedAccountsReadModelEntity {
  @Id @Column(name = "email") var email: String? = null

  @Column(name = "user_id") var user_id: String? = null

  @Column(name = "name") var name: String? = null
}

data class ConfirmedAccountsReadModel(val data: List<ConfirmedAccountsReadModelEntity>)
