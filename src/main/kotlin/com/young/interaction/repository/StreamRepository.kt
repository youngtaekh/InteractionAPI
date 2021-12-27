package com.young.interaction.repository

import com.young.interaction.model.StreamModel
import org.springframework.data.repository.CrudRepository

interface StreamRepository: CrudRepository<StreamModel, Int> {
    fun findAllBy(): List<StreamModel>?
    fun findStreamModelByMaster(master: String): StreamModel?
    fun removeStreamModelByStreamKey(streamKey: String): Any?
    fun deleteAllBy()
    fun removeAllBy()
}
