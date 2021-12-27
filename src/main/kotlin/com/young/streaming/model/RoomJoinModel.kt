package com.young.streaming.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "room_join")
data class RoomJoinModel(
    @Id
    @GeneratedValue
    val id: Long,
    val roomJoinId: String,
    val userId: String,
    val roomId: String,
    val status: Int,
): BaseTimeEntity()
