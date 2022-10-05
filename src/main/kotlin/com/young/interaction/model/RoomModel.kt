package com.young.interaction.model

import com.young.interaction.constants.RoomStatus
import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "room")
data class RoomModel(
    @Id
    @GeneratedValue
    val id: Long?,
    var roomId: String?,
    var ownerId: String?,
    var title: String?,
    var description: String?,
    var status: String?,
    var password: String?
): BaseTimeEntity() {
    init {
        this.status = RoomStatus.ACTIVE
        this.description = ""
        this.password = ""
    }
    override fun toString(): String {
        val builder = java.lang.StringBuilder("Room(")
        if (id != null) builder.append("id: $id")
        if (!roomId.isNullOrEmpty()) builder.append("roomId: $roomId, ")
        if (!ownerId.isNullOrEmpty()) builder.append("ownerId: $ownerId, ")
        if (!title.isNullOrEmpty()) builder.append("title: $title, ")
        if (!description.isNullOrEmpty()) builder.append("description: $description, ")
        if (!password.isNullOrEmpty()) builder.append("password: $password, ")
        builder.append("status: $status)")
        return builder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as RoomModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
