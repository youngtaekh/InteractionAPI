package com.young.interaction.service

import com.young.interaction.model.RoomModel
import org.springframework.stereotype.Service
import java.util.*

@Service
interface RoomService {
    fun saveRoom(roomModel: RoomModel): Optional<RoomModel>

    fun getAllRoom(): List<RoomModel>
    fun getRoomsByRoomStatus(status: String): List<RoomModel>
    fun getRoomByRoomId(roomId: String): Optional<RoomModel>

    fun updateRoomStatus(roomId: String, status: String): Optional<RoomModel>
    fun updateRoomTitle(roomId: String, title: String): Optional<RoomModel>
    fun updateRoomDescription(roomId: String, description: String): Optional<RoomModel>
    fun updateRoomPassword(roomId: String, password:String): Optional<RoomModel>

    fun removeRoom(roomId: String)
    fun removeAllRoom()
}
