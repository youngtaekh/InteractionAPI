package com.young.interaction.model.response

import com.young.interaction.model.CallModel
import com.young.interaction.model.RoomModel
import com.young.interaction.service.CallService

class RoomResponse() {
    var roomId: String? = null
    var ownerId: String? = null
    var title: String? = null
    var description: String? = null
    var status: String? = null
    var password: String? = null
    var calls: MutableList<CallModel> = arrayListOf()

    constructor(room: RoomModel): this() {
        this.roomId = room.roomId
        this.ownerId = room.ownerId
        this.title = room.title
        this.description = room.description
        this.status = room.status
        this.password = room.password
    }

    companion object {
        fun makeResponse(room: RoomModel, service: CallService): RoomResponse {
            val response = RoomResponse(room)
            response.calls.addAll(service.getCallsByRoomId(room.roomId!!))
            return response
        }
    }
}
