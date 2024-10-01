package com.annotation.flea.mapper.`in`

import com.annotation.flea.domain.entity.*
import com.annotation.flea.presentation.dto.ProductDTO
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class ProductDtoMapper {
    fun mapToProductDto(product: Product) : ProductDTO {
        return ProductDTO(
            id = product.id,
            title = product.title,
            description = product.description,
            price = product.price.toString(),
            categoryId = product.category.id,
            categoryName = product.category.name,
            categoryDepth = product.category.depth.toString(),
            serialNumber = product.serialNumber,
            images = product.images.map { it.imageUrl },
            createdAt = product.createdAt,
            updatedAt = product.createdAt,
            soldOut = if(product.isSold) "T" else "F",
            seller = product.seller.username,
        )
    }
    fun mapToDomain(productDto: ProductDTO, user: User) : Product {
        return Product(
            id = productDto.id,
            title = productDto.title,
            description = productDto.description,
            price = productDto.price.toInt(),
            category = Category(
                id = productDto.categoryId,
                name = productDto.categoryName,
                depth = productDto.categoryDepth.toInt()
            ),
            serialNumber = productDto.serialNumber,
            seller = user,
            images = productDto.images.map { Image(imageUrl = it, owner = user, type = ImageType.PRODUCT) },
            createdAt = productDto.createdAt,
            updatedAt = productDto.updatedAt
        )
    }
}