package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.LoadProductPort
import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.mapper.out.CategoryMapper
import com.annotation.flea.mapper.out.ProductMapper
import com.annotation.flea.persistence.repository.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
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
    override fun loadProductsByCategory(page: Int, category: Category): List<Product>? {
        val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, category.name))
        val categoryEntity = categoryMapper.mapToCategoryEntity(category)
        val entities = productRepository.findByCategory(categoryEntity, pageable)
        return entities.map {
            productMapper.mapToProductDomain(it)
        }.content
    }
    override fun loadAllProducts(page: Int, criteria: String): List<Product>? {
        val pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, criteria))
        val entities = productRepository.findAllByOrderByCreatedAtDesc(pageable)
        return entities.map {
            productMapper.mapToProductDomain(it)
        }.content
    }
}