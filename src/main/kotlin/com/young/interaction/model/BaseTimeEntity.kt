package com.young.interaction.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity(
    @CreatedDate
    @Column(name = "created_date")
    var createdDate: LocalDateTime? = null,
    @LastModifiedDate
    @Column(name = "modified_date")
    var modifiedDate: LocalDateTime? = null
) {
//    @CreatedDate
//    var createdDate: LocalDateTime? = null
//    private set
//    @LastModifiedDate
//    var modifiedDate: LocalDateTime? = null
//    private set
}
