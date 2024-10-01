package com.annotation.flea.persistence.entity

import com.annotation.flea.domain.entity.Image
import com.annotation.flea.domain.entity.ImageType
import com.annotation.flea.domain.entity.User
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "product_image")
class ProductImageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val product : UUID,

    val imageUrl : String,

) : BaseTimeEntity() {
    fun mapToDomain(user: User) : Image {
        return Image(
            imageUrl = imageUrl,
            owner = user,
            type = ImageType.PRODUCT
        )
    }
}