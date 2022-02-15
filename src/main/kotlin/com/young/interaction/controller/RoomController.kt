package com.young.interaction.controller

import com.young.interaction.constants.RoomJoinStatus
import com.young.interaction.constants.RoomStatus
import com.young.interaction.model.RoomJoinModel
import com.young.interaction.model.RoomModel
import com.young.interaction.model.response.Response
import com.young.interaction.service.RoomJoinService
import com.young.interaction.service.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/room"])
class RoomController {
    @Autowired
    lateinit var roomService: RoomService
    @Autowired
    lateinit var roomJoinService: RoomJoinService

    @PostMapping
    fun createRoom(@RequestBody roomModel: RoomModel): ResponseEntity<Any> {
        //ownerId, title, description, password check
        roomModel.roomId = System.currentTimeMillis().toString(16)
        roomModel.status = RoomStatus.ACTIVE
        println("POST a room roomId: ${roomModel.roomId}")

        val result = roomService.saveRoom(roomModel)
        return Response.success(result)
    }

    @PostMapping("/join")
    fun joinRoom(@RequestBody roomJoinModel: RoomJoinModel): ResponseEntity<Any> {
        //roomId, userId, callId check
        roomJoinModel.roomJoinId = System.currentTimeMillis().toString(16)
        roomJoinModel.status = RoomJoinStatus.JOIN
        println("POST a roomJoin id: ${roomJoinModel.roomJoinId}")

        val participants = roomJoinService.getRoomJoinsByRoomId(roomJoinModel.roomId!!)
        println("Participants list size ${participants.size}")
        for (participant in participants) {
            println("participant ID: ${participant.userId} status: ${participant.status}")
        }
        val result = roomJoinService.saveRoomJoin(roomJoinModel)
        return Response.success(result)
    }

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
                    Response.success(room.get())
                } else {
                    Response.success(arrayListOf<RoomModel>())
                }
            }
            else -> {
                println("GET room no param")
                Response.error("No Param")
            }
        }
    }

    @GetMapping("/active")
    fun getActiveRooms(): ResponseEntity<Any> {
        println("GET active rooms")
        return Response.success(roomService.getRoomsByRoomStatus(RoomStatus.ACTIVE))
    }

    @GetMapping("/join")
    fun getRoomDetail(@RequestParam(required = true) roomId: String): ResponseEntity<Any> {
        println("GET Room Detail")
        return Response.success(roomJoinService.getRoomJoinsByRoomId(roomId))
    }

    @PutMapping("/title")
    fun updateRoomTitle(@RequestBody roomModel: RoomModel?): ResponseEntity<Any> {
        if (roomModel == null || roomModel.roomId.isNullOrEmpty() || roomModel.title.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        println("PUT room title ${roomModel.title}")
        val room = roomService.updateRoomTitle(roomId = roomModel.roomId!!, title = roomModel.title!!)
        return Response.success(room)
    }

    @PutMapping("/description")
    fun updateRoomDescription(@RequestBody roomModel: RoomModel?): ResponseEntity<Any> {
        if (roomModel == null || roomModel.roomId.isNullOrEmpty() || roomModel.description.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        println("PUT room description ${roomModel.description}")
        val room = roomService.updateRoomDescription(
            roomId = roomModel.roomId!!,
            description = roomModel.description!!)
        return Response.success(room)
    }

    @PutMapping("/join/status")
    fun updateRoomJoinStatus(@RequestBody roomJoinModel: RoomJoinModel?): ResponseEntity<Any> {
        if (roomJoinModel == null || roomJoinModel.roomJoinId.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        println("PUT room join status ${roomJoinModel.status}")
        val roomJoin = roomJoinService.updateRoomJoinStatus(roomJoinModel.roomJoinId!!, roomJoinModel.status!!)
        return Response.success(roomJoin)
    }

    @PutMapping("/status")
    fun updateRoomStatus(@RequestBody roomModel: RoomModel?): ResponseEntity<Any> {
        if (roomModel == null || roomModel.roomId.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        println("PUT room status ${roomModel.status}")
        val room = roomService.updateRoomStatus(roomId = roomModel.roomId!!, status = roomModel.status!!)
        return Response.success(room)
    }

    @PutMapping("/password")
    fun updateRoomPassword(@RequestBody roomModel: RoomModel?): ResponseEntity<Any> {
        if (roomModel == null || roomModel.roomId.isNullOrEmpty() || roomModel.password.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        println("PUT room password ${roomModel.password}")
        val room = roomService.updateRoomPassword(roomId = roomModel.roomId!!, password = roomModel.password!!)
        return Response.success(room)
    }

    @DeleteMapping
    fun deleteRoom(@RequestParam(required = true) roomId: String?): ResponseEntity<Any> {
        return if (roomId.isNullOrEmpty()) {
            println("DELETE no roomId")
            Response.error("No roomId")
        } else {
            println("DELETE room $roomId")
            roomService.removeRoom(roomId)
            Response.success("OK")
        }
    }
}
