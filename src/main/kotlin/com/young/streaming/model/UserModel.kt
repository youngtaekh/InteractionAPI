package com.young.streaming.model

import javax.persistence.*

@Entity
@Table(name = "user")
data class UserModel(
    @Id
    @GeneratedValue
    val id: Long,
    val userId: String,
    val email: String,
    val name: String,
    val password: String,
): BaseTimeEntity()
