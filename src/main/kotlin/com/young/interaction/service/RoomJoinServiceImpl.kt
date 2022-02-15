package com.young.interaction.service

import com.young.interaction.model.RoomJoinModel
import com.young.interaction.repository.RoomJoinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class RoomJoinServiceImpl: RoomJoinService {
    @Autowired
    lateinit var roomJoinRepository: RoomJoinRepository

    @Transactional
    override fun saveRoomJoin(roomJoinModel: RoomJoinModel): Optional<RoomJoinModel> {
        roomJoinRepository.save(roomJoinModel)
        return roomJoinRepository.findRoomJoinModelByRoomJoinId(roomJoinModel.roomJoinId!!)
    }

    override fun getAllRoomJoin(): List<RoomJoinModel> {
        return roomJoinRepository.findAllBy()
    }

    override fun getRoomJoinsByUserId(userId: String): List<RoomJoinModel> {
        return roomJoinRepository.findRoomJoinModelsByUserId(userId)
    }

    override fun getRoomJoinsByRoomId(roomId: String): List<RoomJoinModel> {
        return roomJoinRepository.findRoomJoinModelsByRoomId(roomId)
    }

    override fun getRoomJoinByRoomJoinId(roomJoinId: String): Optional<RoomJoinModel> {
        return roomJoinRepository.findRoomJoinModelByRoomJoinId(roomJoinId)
    }

    override fun updateRoomJoinStatus(roomJoinId: String, status: String): Optional<RoomJoinModel> {
        val roomJoin = roomJoinRepository.findRoomJoinModelByRoomJoinId(roomJoinId)
        if (roomJoin.isPresent) {
            roomJoin.get().status = status.uppercase()
            roomJoinRepository.save(roomJoin.get())
        }
        return roomJoin
    }

    @Transactional
    override fun removeRoomJoin(roomJoinId: String) {
        roomJoinRepository.removeRoomJoinModelByRoomJoinId(roomJoinId)
    }

    @Transactional
    override fun removeAllRoomJoin() {
        roomJoinRepository.removeAllBy()
    }
}
