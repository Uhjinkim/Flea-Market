package com.annotation.flea.persistence.entity

import com.annotation.flea.domain.entity.Image
import com.annotation.flea.domain.entity.ImageType
import com.annotation.flea.domain.entity.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "profile_image")
class ProfileImageEntity(

    @Id
    @Column(name = "user_id")
    val userId: UUID,

    @Column(name = "image_url")
    val imageUrl: String,

    ) : BaseTimeEntity() {
        fun mapToDomain(user: User) : Image {
            return Image(
                owner = user,
                imageUrl = imageUrl,
                type = ImageType.PROFILE
            )
        }
    }