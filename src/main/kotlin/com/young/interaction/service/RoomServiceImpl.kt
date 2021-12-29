package com.young.interaction.service

import com.young.interaction.model.RoomModel
import com.young.interaction.repository.RoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class RoomServiceImpl: RoomService {
    @Autowired
    lateinit var roomRepository: RoomRepository

    @Transactional
    override fun saveRoom(roomModel: RoomModel): Optional<RoomModel> {
        roomRepository.save(roomModel)
        return roomRepository.findRoomModelByRoomId(roomModel.roomId!!)
    }

    override fun getAllRoom(): List<RoomModel> {
        return roomRepository.findAllBy()
    }

    override fun getRoomsByRoomStatus(roomStatus: String): List<RoomModel> {
        return roomRepository.findAllByStatus(roomStatus)
    }

    override fun getRoomByRoomId(roomId: String): Optional<RoomModel> {
        return roomRepository.findRoomModelByRoomId(roomId)
    }

    override fun updateRoomStatus(roomId: String, status: String): Optional<RoomModel> {
        val room = roomRepository.findRoomModelByRoomId(roomId)
        if (room.isPresent) {
            room.get().status = status
            roomRepository.save(room.get())
        }
        return room
    }

    override fun updateRoomTitle(roomId: String, title: String): Optional<RoomModel> {
        val room = roomRepository.findRoomModelByRoomId(roomId)
        if (room.isPresent) {
            room.get().title = title
            roomRepository.save(room.get())
        }
        return room
    }

    override fun updateRoomDescription(roomId: String, description: String): Optional<RoomModel> {
        val room = roomRepository.findRoomModelByRoomId(roomId)
        if (room.isPresent) {
            room.get().description = description
            roomRepository.save(room.get())
        }
        return room
    }

    override fun updateRoomPassword(roomId: String, password: String): Optional<RoomModel> {
        val room = roomRepository.findRoomModelByRoomId(roomId)
        if (room.isPresent) {
            room.get().password = password
            roomRepository.save(room.get())
        }
        return room
    }

    @Transactional
    override fun removeRoom(roomId: String) {
        roomRepository.removeRoomModelByRoomId(roomId)
    }

    @Transactional
    override fun removeAllRoom() {
        roomRepository.removeAllBy()
    }
}
