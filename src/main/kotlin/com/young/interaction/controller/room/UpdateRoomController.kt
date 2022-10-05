package com.young.interaction.controller.room

import com.young.interaction.model.RoomModel
import com.young.interaction.model.response.EmptyBody
import com.young.interaction.model.response.Response
import com.young.interaction.service.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/room"])
class UpdateRoomController {
    @Autowired
    lateinit var roomService: RoomService

    @PutMapping("/title")
    fun updateRoomTitle(@RequestBody requestRoom: RoomModel?): ResponseEntity<Any> {
        if (requestRoom == null || requestRoom.roomId.isNullOrEmpty() || requestRoom.title.isNullOrEmpty()) {
            return Response.error("No Param", EmptyBody())
        }
        println("PUT room title ${requestRoom.title}")
        val room = roomService.updateRoomTitle(roomId = requestRoom.roomId!!, title = requestRoom.title!!)
        return Response.success(room)
    }

    @PutMapping("/description")
    fun updateRoomDescription(@RequestBody requestRoom: RoomModel?): ResponseEntity<Any> {
        if (requestRoom == null || requestRoom.roomId.isNullOrEmpty() || requestRoom.description.isNullOrEmpty()) {
            return Response.error("No Param", EmptyBody())
        }
        println("PUT room description ${requestRoom.description}")
        val room = roomService.updateRoomDescription(
            roomId = requestRoom.roomId!!,
            description = requestRoom.description!!)
        return Response.success(room)
    }

    @PutMapping("/status")
    fun updateRoomStatus(@RequestBody requestRoom: RoomModel?): ResponseEntity<Any> {
        if (requestRoom == null || requestRoom.roomId.isNullOrEmpty()) {
            return Response.error("No Param", EmptyBody())
        }
        println("PUT room status ${requestRoom.status}")
        val room = roomService.updateRoomStatus(roomId = requestRoom.roomId!!, status = requestRoom.status!!)
        return Response.success(room)
    }

    @PutMapping("/password")
    fun updateRoomPassword(@RequestBody requestRoom: RoomModel?): ResponseEntity<Any> {
        if (requestRoom == null || requestRoom.roomId.isNullOrEmpty() || requestRoom.password.isNullOrEmpty()) {
            return Response.error("No Param", EmptyBody())
        }
        println("PUT room password ${requestRoom.password}")
        val room = roomService.updateRoomPassword(roomId = requestRoom.roomId!!, password = requestRoom.password!!)
        return Response.success(room)
    }
}
