package com.young.streaming.service

import com.young.streaming.model.StreamModel
import com.young.streaming.repository.StreamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class StreamServiceImpl constructor(@Autowired private val streamRepository: StreamRepository): StreamService {
    override fun getAllStreams(): List<StreamModel>? =
        streamRepository.findAllBy()

    override fun getStream(id: Int): StreamModel? =
        streamRepository.findById(id).orElse(null)

    override fun getStreamByMaster(master: String): StreamModel? =
        streamRepository.findStreamModelByMaster(master)

    @Transactional
    override fun removeStreamByKey(streamKey: String) {
        val result = streamRepository.removeStreamModelByStreamKey(streamKey)
        println("result $result")
    }

    @Transactional
    override fun deleteAllStreams() {
        streamRepository.deleteAllBy()
    }

    @Transactional
    override fun removeAllStreams() {
        streamRepository.removeAllBy()
    }

    @Transactional
    override fun saveStream(streamModel: StreamModel): StreamModel {
        return streamRepository.save(streamModel)
    }
}
