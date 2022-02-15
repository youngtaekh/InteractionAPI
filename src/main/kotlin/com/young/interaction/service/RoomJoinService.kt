package com.young.interaction.service

import com.young.interaction.model.RoomJoinModel
import org.springframework.stereotype.Service
import java.util.*

@Service
interface RoomJoinService {
    fun saveRoomJoin(roomJoinModel: RoomJoinModel): Optional<RoomJoinModel>

    fun getAllRoomJoin(): List<RoomJoinModel>
    fun getRoomJoinsByUserId(userId: String): List<RoomJoinModel>
    fun getRoomJoinsByRoomId(roomId: String): List<RoomJoinModel>
    fun getRoomJoinByRoomJoinId(roomJoinId: String): Optional<RoomJoinModel>

    fun updateRoomJoinStatus(roomJoinId: String, status: String): Optional<RoomJoinModel>

    fun removeRoomJoin(roomJoinId: String)
    fun removeAllRoomJoin()
}
