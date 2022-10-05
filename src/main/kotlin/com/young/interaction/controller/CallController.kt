package com.young.interaction.controller

import com.young.interaction.constants.CallStatus
import com.young.interaction.constants.CallStatus.Companion.TERMINATED
import com.young.interaction.constants.RoomStatus.Companion.INACTIVE
import com.young.interaction.model.CallModel
import com.young.interaction.model.response.EmptyBody
import com.young.interaction.model.response.Response
import com.young.interaction.service.CallService
import com.young.interaction.service.RoomService
import com.young.interaction.util.Identity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/call"])
class CallController {
    @Autowired
    lateinit var roomService: RoomService
    @Autowired
    lateinit var callService: CallService

    @PostMapping
    fun setCall(@RequestBody requestModel: CallModel): ResponseEntity<Any> {
        //userId, roomId check
        requestModel.callId = Identity.get()
        requestModel.status = CallStatus.IDLE
        println("POST a call callId: ${requestModel.callId}")
        if (requestModel.sdp.isNullOrEmpty()) {
            requestModel.sdp = NONE_SDP
        }
        if (requestModel.ice.isNullOrEmpty()) {
            requestModel.ice = NONE_ICE
        }

        val calls = callService.getCallsByRoomId(roomId = requestModel.roomId!!)
        for (call in calls) {
            if (call.userId == requestModel.userId) {
                return Response.error("Call Already Exist")
            }
        }

        val result = callService.saveCall(requestModel)
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
    fun updateCallStatus(@RequestBody requestModel: CallModel?): ResponseEntity<Any> {
        if (requestModel == null || requestModel.callId.isNullOrEmpty()) {
            return Response.error("No Param", EmptyBody())
        }
        println("PUT call status ${requestModel.status}")
        val call = callService.updateCallStatus(callId = requestModel.callId!!, status = requestModel.status!!)
        val calls = callService.getCallsByRoomId(call.get().roomId!!)

        var isRoomInactive = true
        for (callModel in calls) {
            println("callModel $callModel")
            println("status ${callModel.status}, $TERMINATED")
            if (callModel.status?.uppercase() != TERMINATED.uppercase()) {
                println("different")
                isRoomInactive = false
            }
        }
        if (isRoomInactive) {
            println("room ${roomService.updateRoomStatus(call.get().roomId!!, INACTIVE)}")
        }
        return Response.success(call)
    }

    @PutMapping("/sdp")
    fun updateCallSDP(@RequestBody requestModel: CallModel?): ResponseEntity<Any> {
        if (requestModel == null || requestModel.callId.isNullOrEmpty() || requestModel.sdp.isNullOrEmpty()) {
            return Response.error("No Param", EmptyBody())
        }
        callService.updateCallSDP(callId = requestModel.callId!!, sdp = requestModel.sdp!!)
        return Response.success(EmptyBody())
    }

    @PutMapping("/ice")
    fun updateCallICE(@RequestBody requestModel: CallModel?): ResponseEntity<Any> {
        if (requestModel == null || requestModel.callId.isNullOrEmpty() || requestModel.ice.isNullOrEmpty()) {
            return Response.error("No Param", EmptyBody())
        }
        callService.updateCallICE(requestModel.callId!!, requestModel.ice!!)
        return Response.success(EmptyBody())
    }

    @DeleteMapping
    fun deleteCall(@RequestParam(required = true) callId: String?): ResponseEntity<Any> {
        return if (callId.isNullOrEmpty()) {
            println("DELETE no callId")
            Response.error("No callId", EmptyBody())
        } else {
            println("DELETE call $callId")
            callService.removeCall(callId)
            Response.success(EmptyBody())
        }
    }

    companion object {
        private const val NONE_SDP = ""
        private const val NONE_ICE = ""
    }
}
