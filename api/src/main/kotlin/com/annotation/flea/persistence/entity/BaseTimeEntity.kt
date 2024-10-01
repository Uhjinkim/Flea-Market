package com.annotation.flea.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseTimeEntity {

    @Column(name = "created_at")
    @CreationTimestamp
    var createdAt : LocalDateTime = LocalDateTime.MIN

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt : LocalDateTime = LocalDateTime.MIN

    @PrePersist
    fun prePersist() {
        createdAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}