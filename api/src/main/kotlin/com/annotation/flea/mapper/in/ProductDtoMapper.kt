package com.annotation.flea.mapper.`in`

import com.annotation.flea.presentation.dto.ProductDTO
import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.domain.entity.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class ProductDtoMapper {
    fun mapToProductDto(product: Product) : ProductDTO {
        return ProductDTO(
            title = product.title,
            description = product.description,
            price = product.price.toString(),
            categoryId = product.category.id,
            categoryName = product.category.name,
            serialNumber = product.serialNumber,
            images = product.images,
            createdAt = product.createdAt.toString(),
            updatedAt = product.createdAt.toString(),
            soldOut = if(product.isSold) "T" else "F",
            seller = product.seller.username,
        )
    }
    fun mapToProduct(productDto: ProductDTO, user: User) : Product {
        return Product(
            id = UUID.randomUUID(),
            title = productDto.title,
            description = productDto.description,
            price = productDto.price.toInt(),
            category = Category(id = productDto.categoryId, name = productDto.categoryName, parent = null),
            serialNumber = productDto.serialNumber,
            seller = user,
            images = productDto.images,
            createdAt = LocalDateTime.now(),
        )
    }
}