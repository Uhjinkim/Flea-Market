package com.annotation.flea.mapper.out

import com.annotation.flea.domain.entity.Image
import com.annotation.flea.persistence.entity.ProductImageEntity
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProductImageMapper {
    fun mapToImageEntity(image: Image, productId : UUID) : ProductImageEntity {
        return ProductImageEntity(
            imageUrl = image.imageUrl,
            product = productId,
        )

    }
}