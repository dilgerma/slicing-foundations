package de.eventmodelers.registration.emailstoconfirmtodo

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

data class EmailsToConfirmTODOReadModelQuery(val aggregateId: String)

/*
Boardlink: https://miro.com/app/board/uXjVJo5Vvho=/?moveToWidget=3458764655601747828
*/
@Entity
class EmailsToConfirmTODOReadModelEntity {
  @Id @Column(name = "email") var email: String? = null

  @Column(name = "token") var token: String? = null

  @Column(name = "user_id") var userId: String? = null
}

data class EmailsToConfirmTODOReadModel(val data: EmailsToConfirmTODOReadModelEntity)
