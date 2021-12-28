package com.young.interaction.repository

import com.young.interaction.model.CallModel
import com.young.interaction.model.UserModel
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CallRepository: CrudRepository<CallModel, Int> {
    fun findAllBy(): List<CallModel>
    fun findCallModelsByUserId(userId: String): List<CallModel>
    fun findCallModelsByRoomId(roomId: String): List<CallModel>
    fun findCallModelByCallId(callId: String): Optional<CallModel>

    fun removeCallModelByCallId(callId: String): Any?
    fun removeAllBy(): Any?
}
