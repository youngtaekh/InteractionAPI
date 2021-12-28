package com.young.interaction.service

import com.young.interaction.model.CallModel
import com.young.interaction.model.UserModel
import org.springframework.stereotype.Service
import java.util.*

@Service
interface CallService {
    fun saveCall(callModel: CallModel): Optional<CallModel>

    fun getAllCall(): List<CallModel>
    fun getCallsByUserId(userId: String): List<CallModel>
    fun getCallsByRoomId(roomId: String): List<CallModel>
    fun getCallByCallId(callId: String): Optional<CallModel>

    fun updateCallStatus(callId: String, status: String): Optional<CallModel>
    fun updateCallSDP(callId: String, sdp: String): Optional<CallModel>

    fun removeCall(callId: String)
    fun removeAllCall()
}
