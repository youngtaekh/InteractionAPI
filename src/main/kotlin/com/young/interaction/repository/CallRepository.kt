package com.young.interaction.repository

import com.young.interaction.model.CallModel
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*
import javax.persistence.LockModeType
import javax.transaction.Transactional

interface CallRepository: CrudRepository<CallModel, Int> {
    fun findAllBy(): List<CallModel>
    fun findCallModelsByUserId(userId: String): List<CallModel>
    fun findCallModelsByRoomId(roomId: String): List<CallModel>
    fun findCallModelByCallId(callId: String): Optional<CallModel>

//    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
//    @Query(value = "select * from call_model c where c.call_id = :callId for update", nativeQuery = true)
//    fun findByCallIdForUpdate(callId: String): Optional<CallModel>

    @Modifying
    @Query(value = "update call_model c set c.sdp = :sdp where c.call_id = :callId", nativeQuery = true)
    fun updateCallSDP(@Param("callId") callId: String, @Param("sdp") sdp: String): Any?
    @Modifying
    @Query(value = "update call_model c set c.ice = :ice where c.call_id = :callId", nativeQuery = true)
    fun updateCallICE(@Param("callId") callId: String, @Param("ice") ice: String): Any?

    fun removeCallModelsByRoomId(roomId: String): Any?
    fun removeCallModelByCallId(callId: String): Any?
    fun removeAllBy(): Any?
}
