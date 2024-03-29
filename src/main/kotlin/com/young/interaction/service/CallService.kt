package com.young.interaction.service

import com.young.interaction.model.CallModel
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
    fun updateCallSDP(callId: String, sdp: String)
    fun updateCallICE(callId: String, ice: String)

    fun removeCall(callId: String)
    fun removeCallsByRoomId(roomId: String)
    fun removeAllCall()
}
