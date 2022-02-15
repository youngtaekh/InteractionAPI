package com.young.interaction.controller

import com.young.interaction.constants.CallStatus
import com.young.interaction.model.CallModel
import com.young.interaction.model.response.Response
import com.young.interaction.service.CallService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/call"])
class CallController {
    @Autowired
    lateinit var callService: CallService

    @PostMapping
    fun setCall(@RequestBody callModel: CallModel): ResponseEntity<Any> {
        //userId, roomId check
        callModel.callId = System.currentTimeMillis().toString(16)
        callModel.status = CallStatus.IDLE
        println("POST a call callId: ${callModel.callId}")

        val calls = callService.getCallsByRoomId(roomId = callModel.roomId!!)
        for (call in calls) {
            if (call.userId == callModel.userId) {
                return Response.error("Call Already Exist")
            }
        }

        val result = callService.saveCall(callModel)
        return Response.success(result)
    }

    @GetMapping
    fun getCalls(@RequestParam(required = false) userId: String?,
                 @RequestParam(required = false) roomId: String?,
                 @RequestParam(required = false) callId: String?): ResponseEntity<Any> {
        return when {
            userId != null -> {
                println("GET calls userId: $userId")
                val calls = callService.getCallsByUserId(userId)
                Response.success(calls)
            }
            roomId != null -> {
                println("GET calls roomId: $roomId")
                val calls = callService.getCallsByRoomId(roomId)
                Response.success(calls)
            }
            callId != null -> {
                println("GET call callId: $callId")
                val call = callService.getCallByCallId(callId)
                Response.success(call)
            }
            else -> {
                println("GET call no param")
                Response.error("No Param")
            }
        }
    }

    @PutMapping("/status")
    fun updateCallStatus(@RequestBody callModel: CallModel?): ResponseEntity<Any> {
        if (callModel == null || callModel.callId.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        println("PUT call status ${callModel.status}")
        val call = callService.updateCallStatus(callId = callModel.callId!!, status = callModel.status!!)
        return Response.success(call)
    }

    @PutMapping("/sdp")
    fun updateCallSDP(@RequestBody callModel: CallModel?): ResponseEntity<Any> {
        if (callModel == null || callModel.callId.isNullOrEmpty() || callModel.sdp.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        println("PUT call sdp")
        val call = callService.updateCallSDP(callId = callModel.callId!!, sdp = callModel.sdp!!)
        return Response.success(call)
    }

    @DeleteMapping
    fun deleteCall(@RequestParam(required = true) callId: String?): ResponseEntity<Any> {
        return if (callId.isNullOrEmpty()) {
            println("DELETE no callId")
            Response.error("No callId")
        } else {
            println("DELETE call $callId")
            callService.removeCall(callId)
            Response.success("OK")
        }
    }
}
