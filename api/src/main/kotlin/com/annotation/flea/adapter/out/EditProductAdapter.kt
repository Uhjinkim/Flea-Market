package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.EditProductPort
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.mapper.out.CategoryMapper
import com.annotation.flea.mapper.out.ImageMapper
import com.annotation.flea.mapper.out.ProductMapper
import com.annotation.flea.persistence.repository.ImageRepository
import com.annotation.flea.persistence.repository.ProductRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class EditProductAdapter(
    private val productRepository: ProductRepository,
    private val imageAdapter: ImageAdapter,
    private val categoryMapper: CategoryMapper,
) : EditProductPort {
    override fun editProduct(product: Product): Boolean {
        if(product.isSold) {
            println("Product is already sold")
            return false
        }
        val entity = productRepository.findById(product.id).orElseThrow {
            NoSuchElementException("Product not found")
        }
        entity.title = product.title
        entity.description = product.description
        entity.price = product.price
        imageAdapter.syncProductImage(entity, product.images)
        entity.category = categoryMapper.mapToCategoryEntity(product.category)
        entity.serialNumber = product.serialNumber
        productRepository.save(entity)
        return true
    }

}