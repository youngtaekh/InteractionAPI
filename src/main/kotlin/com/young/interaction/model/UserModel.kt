package com.young.interaction.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class UserModel(
    @Id
    @GeneratedValue
    val id: Long,
    val userId: String?,
    var email: String?,
    var name: String?,
    var password: String?,
): BaseTimeEntity()
