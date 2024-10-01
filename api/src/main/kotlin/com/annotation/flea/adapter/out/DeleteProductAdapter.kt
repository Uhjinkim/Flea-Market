package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.DeleteProductPort
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.persistence.repository.ProductRepository

class DeleteProductAdapter(
    private val productRepository: ProductRepository,
) : DeleteProductPort {
    override fun deleteProduct(product: Product) : Boolean {
        if (product.isSold) {
            println("Product is sold, cannot be deleted")
            return false
        }
        val entity = productRepository.findById(product.id!!).orElseThrow {
            throw Exception("Product not found")
        }
        productRepository.delete(entity)
        return true
    }
}