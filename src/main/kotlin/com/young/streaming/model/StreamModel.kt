package com.young.streaming.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "stream")
data class StreamModel(
    @Id
    @GeneratedValue
    val id: Long,
    //stream id(name = id)
    var streamId: String,
    //key for send stream
    var streamKey: String,
    //id for receive stream
    var playbackId: String,
    val master: String,
    var count: Int,
    var title: String,
    var thumb: String,
    var password: String,
    //stream status
    var status: String,
    //stream policy
    var policy: String,
): BaseTimeEntity()
