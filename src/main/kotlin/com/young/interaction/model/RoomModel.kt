package com.young.interaction.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "room")
data class RoomModel(
    @Id
    @GeneratedValue
    val id: Long,
    val roomId: String?,
    var title: String?,
    var description: String?,
    var roomStatus: String?
): BaseTimeEntity()
