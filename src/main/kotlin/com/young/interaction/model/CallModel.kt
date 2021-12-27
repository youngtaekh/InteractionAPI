package com.young.interaction.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "call")
data class CallModel(
    @Id
    @GeneratedValue
    val id: Long,
    val callId: String,
    val sdp: String,
    val userId: String,
    val roomId: String,
): BaseTimeEntity()
