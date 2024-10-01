package com.annotation.flea.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "profile_image")
class ProfileImageEntity(
    @OneToOne
    @JoinColumn(name = "user_id")
    val userId: UserEntity,

    @Id
    @Column(name = "image_url")
    val imageUrl: String,

    @Column(name = "created_at")
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now(),

)