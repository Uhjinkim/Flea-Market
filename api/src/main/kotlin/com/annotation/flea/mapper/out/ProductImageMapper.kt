package com.annotation.flea.mapper.out

import com.annotation.flea.persistence.entity.ProductEntity
import com.annotation.flea.persistence.entity.ProductImageEntity
import org.springframework.stereotype.Component

@Component
class ProductImageMapper {
    fun mapToImageEntity(image: String, product: ProductEntity) : ProductImageEntity {
        return ProductImageEntity(
            imageUrl = image,
            product = product,
        )

    }
    fun mapToImageDomain(entity: ProductImageEntity) : String {
        return entity.imageUrl
    }
}