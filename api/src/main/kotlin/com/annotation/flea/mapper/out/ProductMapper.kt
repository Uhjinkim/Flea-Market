package com.annotation.flea.mapper.out

import com.annotation.flea.domain.entity.Product
import com.annotation.flea.persistence.entity.ProductEntity
import com.fasterxml.uuid.Generators
import org.springframework.stereotype.Component

// 도메인 객체 Product, 영속성 엔티티 ProductEntity 간의 매핑을 담당하는 ProductMapper 클래스
@Component
class ProductMapper(
    private val categoryMapper: CategoryMapper,
    private val userMapper: UserMapper,
    private val imageMapper: ProductImageMapper,) {
    fun mapToProductEntity(product : Product) : ProductEntity {
        val entity = ProductEntity(
            id = product.id?: Generators.timeBasedEpochGenerator().generate(),
            title = product.title,
            price = product.price,
            category = categoryMapper.mapToCategoryEntity(product.category),
            serialNumber = product.serialNumber,
            description = product.description,
            seller = userMapper.mapToUserEntity(product.seller),
            isSold = product.isSold,
            createdAt = product.createdAt
        )
        entity.images = product.images.map { imageMapper.mapToImageEntity(it, entity) }.toMutableList()
        return entity
    }
    fun mapToProductDomain(entity: ProductEntity) : Product {
        return Product(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            category = categoryMapper.mapToCategoryDomain(entity.category),
            serialNumber = entity.serialNumber,
            description = entity.description,
            seller = userMapper.mapToUserDomain(entity.seller),
            isSold = entity.isSold,
            createdAt = entity.createdAt,
            images = entity.images.map { imageMapper.mapToImageDomain(it) }.toMutableList(),
        )

    }
}