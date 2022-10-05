package com.young.interaction.model

import com.young.interaction.constants.CallStatus
import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "call_model")
class CallModel: BaseTimeEntity() {

    @Id
    @GeneratedValue
    var id: Long = 0L
    @Column(name = "call_id", unique = true)
    var callId: String? = null
    @Column(name = "user_id", unique = false)
    var userId: String? = null
    @Column(name = "room_id")
    var roomId: String? = null
    var status: String? = CallStatus.IDLE
    @Column(name = "sdp", columnDefinition = "TEXT")
    var sdp: String? = ""
    @Column(columnDefinition = "TEXT")
    var ice: String? = ""

    fun addICECandidate(ice: String) {
        println("CallModel addIceCandidate ice is $ice")
        if (this.ice.isNullOrEmpty()) {
            this.ice = ice
            println("this.ice is empty")
        } else {
            this.ice += ";$ice"
            println("this.ice ain't empty")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CallModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , createdDate = $createdDate , modifiedDate = $modifiedDate , callId = $callId , userId = $userId , roomId = $roomId , status = $status , sdp = $sdp , ice = $ice )"
    }
}
