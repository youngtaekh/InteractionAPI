package com.young.interaction.service

import com.young.interaction.model.CallModel
import com.young.interaction.repository.CallRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class CallServiceImpl: CallService {
    @Autowired
    lateinit var callRepository: CallRepository

    @Transactional
    override fun saveCall(callModel: CallModel): Optional<CallModel> {
        callRepository.save(callModel)
        return callRepository.findCallModelByCallId(callModel.callId!!)
    }

    override fun getAllCall(): List<CallModel> {
        return callRepository.findAllBy()
    }

    override fun getCallsByUserId(userId: String): List<CallModel> {
        return callRepository.findCallModelsByUserId(userId)
    }

    override fun getCallsByRoomId(roomId: String): List<CallModel> {
        return callRepository.findCallModelsByRoomId(roomId)
    }

    override fun getCallByCallId(callId: String): Optional<CallModel> {
        return callRepository.findCallModelByCallId(callId)
    }

    override fun updateCallStatus(callId: String, status: String): Optional<CallModel> {
        val call = callRepository.findCallModelByCallId(callId)
        if (call.isPresent) {
            call.get().callStatus = status
            callRepository.save(call.get())
        }
        return call
    }

    override fun updateCallSDP(callId: String, sdp: String): Optional<CallModel> {
        val call = callRepository.findCallModelByCallId(callId)
        if (call.isPresent) {
            call.get().sdp = sdp
            callRepository.save(call.get())
        }
        return call
    }

    @Transactional
    override fun removeCall(callId: String) {
        callRepository.removeCallModelByCallId(callId)
    }

    @Transactional
    override fun removeAllCall() {
        callRepository.removeAllBy()
    }
}
