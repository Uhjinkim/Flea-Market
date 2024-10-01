package com.annotation.flea.mapper.out

import com.annotation.flea.domain.entity.Product
import com.annotation.flea.persistence.entity.ProductEntity
import com.fasterxml.uuid.Generators
import org.springframework.stereotype.Component

// 도메인 객체 Product, 영속성 엔티티 ProductEntity 간의 매핑을 담당하는 ProductMapper 클래스
@Component
class ProductMapper {
    fun mapToProductEntity(product : Product) : ProductEntity {
        val entity = ProductEntity(
            id = product.id?: Generators.timeBasedEpochGenerator().generate(),
            title = product.title,
            price = product.price,
            category = product.category.id,
            serialNumber = product.serialNumber,
            description = product.description,
            seller = product.seller.username,
            isSold = product.isSold,
        )
        return entity
    }
}