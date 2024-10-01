package com.annotation.flea.mapper.out

import com.annotation.flea.persistence.entity.ProductImageEntity
import com.annotation.flea.persistence.entity.ProfileImageEntity
import com.annotation.flea.persistence.entity.UserEntity
import org.springframework.stereotype.Component

@Component
class ProfileImageMapper {
    fun mapToImageEntity(image: String, profile: UserEntity) : ProfileImageEntity {
        return ProfileImageEntity(
            userId = profile,
            imageUrl = image,
        )
    }
    fun mapToImageDomain(entity: ProfileImageEntity) : String {
        return entity.imageUrl
    }
}