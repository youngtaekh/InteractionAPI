package com.young.interaction.controller.room

import com.young.interaction.constants.RoomStatus
import com.young.interaction.model.RoomModel
import com.young.interaction.model.response.EmptyBody
import com.young.interaction.model.response.Response
import com.young.interaction.model.response.RoomResponse
import com.young.interaction.service.CallService
import com.young.interaction.service.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/room"])
class GetRoomController {
    @Autowired
    lateinit var roomService: RoomService
    @Autowired
    lateinit var callService: CallService

    @GetMapping
    fun getRooms(@RequestParam(required = false) status: String?,
                 @RequestParam(required = false) roomId: String?): ResponseEntity<Any> {
        return when {
            status != null -> {
                println("GET rooms status: $status")
                val rooms = roomService.getRoomsByRoomStatus(status.uppercase())
                Response.success(rooms)
            }
            roomId != null -> {
                println("GET room roomId: $roomId")
                val room = roomService.getRoomByRoomId(roomId)
                if (room.isPresent) {
                    val roomResponse = RoomResponse(room.get())
                    val calls = callService.getCallsByRoomId(roomId = room.get().roomId!!)
                    roomResponse.calls.addAll(calls)

                    Response.success(RoomResponse.makeResponse(room.get(), callService))
                } else {
                    Response.success(arrayListOf<RoomModel>())
                }
            }
            else -> {
                println("GET room no param")
                Response.error("No Param", EmptyBody())
            }
        }
    }

    @GetMapping("/active")
    fun getActiveRooms(): ResponseEntity<Any> {
        println("GET active rooms")
        return Response.success(roomService.getRoomsByRoomStatus(RoomStatus.ACTIVE))
    }
}
