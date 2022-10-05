package com.young.interaction.controller.room

import com.young.interaction.constants.RoomStatus
import com.young.interaction.model.CallModel
import com.young.interaction.model.RoomModel
import com.young.interaction.model.response.EmptyBody
import com.young.interaction.model.response.Response
import com.young.interaction.model.response.RoomResponse
import com.young.interaction.service.CallService
import com.young.interaction.service.RoomService
import com.young.interaction.util.Identity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/room"])
class PostRoomController {
    @Autowired
    lateinit var roomService: RoomService
    @Autowired
    lateinit var callService: CallService

    @PostMapping
    fun enterRoom(@RequestBody requestRoom: RoomModel): ResponseEntity<Any> {
        //ownerId, description check
        if (requestRoom.ownerId.isNullOrEmpty()) {
            return Response.error("No ownerId", EmptyBody())
        }
        if (requestRoom.description.isNullOrEmpty()) {
            requestRoom.description = BASIC_DESCRIPTION
        }
        if (requestRoom.password.isNullOrEmpty()) {
            requestRoom.password = NONE_PASSWORD
        }
        //check title
        return if (requestRoom.title != null) {
            //check password
            val roomResponse: RoomResponse
            var room = roomService.getRoomByTitle(requestRoom.title!!)
            if (room.isEmpty || room.get().status != RoomStatus.ACTIVE) {
                //create room
                println("no room -> create room $requestRoom")
                requestRoom.roomId = Identity.get()
                requestRoom.status = RoomStatus.ACTIVE
                room = roomService.saveRoom(requestRoom)
                println("room $room")
                roomResponse = RoomResponse(room.get())
            } else {
                roomResponse = RoomResponse(room.get())
                val calls = callService.getCallsByRoomId(roomId = room.get().roomId!!)
                for (call in calls) {
                    println("userId ${call.userId}, ownerId ${requestRoom.ownerId}")
                    if (call.userId == requestRoom.ownerId) {
                        return Response.error("Already participate", EmptyBody())
                    }
                }
                roomResponse.calls.addAll(calls)
            }
            val call = CallModel()
            call.callId = Identity.get()
            call.roomId = room.get().roomId
            call.userId = requestRoom.ownerId
            callService.saveCall(call)
            roomResponse.calls.add(call)
            Response.success(roomResponse)
        } else {
            Response.error("no room title", EmptyBody())
        }
    }

    companion object {
        private const val BASIC_DESCRIPTION = "basic description"
        private const val NONE_PASSWORD = ""
    }
}
