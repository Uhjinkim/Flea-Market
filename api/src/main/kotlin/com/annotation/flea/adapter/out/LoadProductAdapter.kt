package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.LoadProductPort
import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.mapper.out.CategoryMapper
import com.annotation.flea.mapper.out.ProductMapper
import com.annotation.flea.persistence.entity.ProductEntity
import com.annotation.flea.persistence.repository.CategoryRepository
import com.annotation.flea.persistence.repository.ProductImageRepository
import com.annotation.flea.persistence.repository.ProductRepository
import com.annotation.flea.persistence.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class LoadProductAdapter(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val imageRepository: ProductImageRepository,
    private val productMapper: ProductMapper,
    private val categoryMapper: CategoryMapper,
) : LoadProductPort {
    override fun loadProductsBySerialNumber(page: Int, serialNumber: String): List<Product> {
        val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC))
        val entities = productRepository.findBySerialNumber(serialNumber, pageable)
        return mapToProductList(entities)
    }
    override fun loadProductsByCategory(page: Int, category: Category): List<Product> {
        val pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, category.name))
        val categoryEntity = categoryMapper.mapToCategoryEntity(category)
        val entities = productRepository.findAllByCategory(categoryEntity.id, pageable)
        return entities.map { entity ->
            val user = userRepository.findByUsername(entity.seller)?.mapToDomain()!!
            val images = imageRepository.findByProduct(entity.id).map {
                it.mapToDomain(user)
            }
            entity.mapToDomain(user, category, images)
        }.content
    }
    override fun loadAllProducts(page: Int, criteria: String): List<Product> {
        val pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, criteria))
        val entities = productRepository.findAllByOrderByCreatedAtDesc(pageable)
        return mapToProductList(entities)
    }
    private fun mapToProductList(entities: Page<ProductEntity>) : List<Product> {
        return entities.map { entity ->
            val user = userRepository.findByUsername(entity.seller)?.mapToDomain()!!
            val category = categoryRepository.findById(entity.category).orElseThrow().mapToDomain()
            val images = imageRepository.findByProduct(entity.id).map {
                it.mapToDomain(user)
            }
            entity.mapToDomain(user, category, images)
        }.content
    }
}