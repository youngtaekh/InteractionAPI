package com.young.interaction.controller.room

import com.young.interaction.model.response.EmptyBody
import com.young.interaction.model.response.Response
import com.young.interaction.service.CallService
import com.young.interaction.service.RoomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/room"])
class DeleteRoomController {
    @Autowired
    lateinit var roomService: RoomService
    @Autowired
    lateinit var callService: CallService

    @DeleteMapping
    fun deleteRoom(@RequestParam(required = true) roomId: String?): ResponseEntity<Any> {
        return if (roomId.isNullOrEmpty()) {
            println("DELETE no roomId")
            Response.error("No roomId", EmptyBody())
        } else {
            println("DELETE room $roomId")
            roomService.removeRoom(roomId)
            callService.removeCallsByRoomId(roomId)
            Response.success(EmptyBody())
        }
    }
}
