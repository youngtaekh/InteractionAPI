package com.young.streaming.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "stream")
data class StreamModel(
    @Id
    @GeneratedValue
    val id: Long,
    val streamKey: String,
    val playbackId: String,
    val master: String,
    val count: Int,
    val title: String,
    val thumb: String,
    val password: String,
//    @CreatedDate
//    val createdDate: LocalDateTime,
//    @LastModifiedDate
//    val modifiedDate: LocalDateTime,
): BaseTimeEntity()
