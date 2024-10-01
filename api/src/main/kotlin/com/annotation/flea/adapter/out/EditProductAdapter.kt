package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.EditProductPort
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.mapper.out.CategoryMapper
import com.annotation.flea.mapper.out.ProductMapper
import com.annotation.flea.persistence.repository.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class EditProductAdapter(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper,
    private val imageAdapter: ImageAdapter,
    private val categoryMapper: CategoryMapper,
) : EditProductPort {
    override fun editProduct(product: Product): Boolean {
        if(product.isSold) {
            println("Product is already sold")
            return false
        }
        val entity = productMapper.mapToProductEntity(product)

        imageAdapter.syncProductImage(product, product.images)
        entity.serialNumber = product.serialNumber
        productRepository.save(entity)
        return true
    }

}