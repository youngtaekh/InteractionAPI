package com.young.interaction.service

import com.young.interaction.model.StreamModel
import org.springframework.stereotype.Service

@Service
interface StreamService {
    fun getAllStreams(): List<StreamModel>?
    fun getStream(id: Int): StreamModel?
    fun saveStream(streamModel: StreamModel): StreamModel
    fun getStreamByMaster(master: String): StreamModel?
    fun removeStreamById(streamId: String)
    fun deleteAllStreams()
    fun removeAllStreams()
}
