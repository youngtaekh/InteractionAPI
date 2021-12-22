package com.young.streaming.service

import com.young.streaming.model.StreamModel
import org.springframework.stereotype.Service

@Service
interface StreamService {
    fun getAllStreams(): List<StreamModel>?
    fun getStream(id: Int): StreamModel?
    fun saveStream(streamModel: StreamModel): StreamModel
    fun getStreamByMaster(master: String): StreamModel?
    fun removeStreamByKey(streamKey: String)
    fun deleteAllStreams()
    fun removeAllStreams()
}
