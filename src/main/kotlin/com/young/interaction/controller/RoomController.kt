package com.young.interaction.controller

import com.young.interaction.model.RoomModel
import com.young.interaction.model.response.Response
import com.young.interaction.service.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/room"])
class RoomController {
    @Autowired
    lateinit var roomService: RoomService

    @PostMapping
    fun setRoom(@RequestBody roomModel: RoomModel): ResponseEntity<Any> {
        //userId, roomId check
        roomModel.roomId = System.currentTimeMillis().toString(16)
        roomModel.status = ACTIVE
        println("POST a room roomId: ${roomModel.roomId}")

        val result = roomService.saveRoom(roomModel)
        return Response.success(result)
    }

    @GetMapping
    fun getRooms(@RequestParam(required = false) status: String?,
                 @RequestParam(required = false) roomId: String?): ResponseEntity<Any> {
        return when {
            status != null -> {
                println("GET rooms status: $status")
                val calls = roomService.getRoomsByRoomStatus(status)
                Response.success(calls)
            }
            roomId != null -> {
                println("GET room roomId: $roomId")
                val calls = roomService.getRoomByRoomId(roomId)
                Response.success(calls)
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
        return Response.success(roomService.getRoomsByRoomStatus(ACTIVE))
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

    @PutMapping("/status")
    fun updateRoomStatus(@RequestBody roomModel: RoomModel?): ResponseEntity<Any> {
        if (roomModel == null || roomModel.roomId.isNullOrEmpty()) {
            return Response.error("No Param")
        }
        if (roomModel.status.isNullOrEmpty()) {
            roomModel.status = END
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

    companion object {
        const val ACTIVE = "ACTIVE"
        const val END = "END"
    }
}
