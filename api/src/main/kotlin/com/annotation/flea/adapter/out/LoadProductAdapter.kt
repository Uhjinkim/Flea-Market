package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.LoadProductPort
import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.mapper.out.CategoryMapper
import com.annotation.flea.mapper.out.ProductMapper
import com.annotation.flea.persistence.repository.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class LoadProductAdapter(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper,
    private val categoryMapper: CategoryMapper,
) : LoadProductPort {
    override fun loadProductsBySerialNumber(serialNumber: String): List<Product>? {
        val entities = productRepository.findBySerialNumber(serialNumber)
        return entities.map {
            productMapper.mapToProductDomain(it)
        }
    }
    override fun loadProductsByCategory(category: Category): List<Product>? {
        val entities = productRepository.findByCategory(categoryMapper.mapToCategoryEntity(category))
        return entities.map {
            productMapper.mapToProductDomain(it)
        }
    }
    override fun loadProductsByPriceRange(minPrice: Int, maxPrice: Int): List<Product>? {
        val entities = productRepository.findByPriceBetween(minPrice, maxPrice)
        return entities.map {
            productMapper.mapToProductDomain(it)
        }
    }
}