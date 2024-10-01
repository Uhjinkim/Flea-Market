package com.annotation.flea.mapper.out

import com.annotation.flea.domain.entity.Image
import com.annotation.flea.domain.entity.User
import com.annotation.flea.persistence.entity.ProfileImageEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class ProfileImageMapper {
    fun mapToImageEntity(image: Image, profile: UUID) : ProfileImageEntity {
        return ProfileImageEntity(
            userId = profile,
            imageUrl = image.imageUrl,
        )
    }
}