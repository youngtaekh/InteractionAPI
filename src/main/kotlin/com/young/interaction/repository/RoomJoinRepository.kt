package com.young.interaction.repository

import com.young.interaction.model.RoomJoinModel
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RoomJoinRepository: CrudRepository<RoomJoinModel, Int> {
    fun findAllBy(): List<RoomJoinModel>
    fun findRoomJoinModelByRoomJoinId(userId: String): Optional<RoomJoinModel>
    fun findRoomJoinModelsByRoomId(roomId: String): List<RoomJoinModel>
    fun findRoomJoinModelsByUserId(callId: String): List<RoomJoinModel>

    fun removeRoomJoinModelByRoomJoinId(callId: String): Any?
    fun removeAllBy(): Any?
}
