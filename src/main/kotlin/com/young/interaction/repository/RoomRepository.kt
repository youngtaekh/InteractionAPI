package com.young.interaction.repository

import com.young.interaction.model.RoomModel
import org.springframework.data.repository.CrudRepository
import java.util.*

interface RoomRepository: CrudRepository<RoomModel, Int> {
    fun findAllBy(): List<RoomModel>
    fun findAllByStatus(roomStatus: String): List<RoomModel>
    fun findRoomModelByRoomId(roomId: String): Optional<RoomModel>

    fun removeRoomModelByRoomId(roomId: String): Any?
    fun removeAllBy(): Any?
}
