package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.CategoryEntity
import com.annotation.flea.persistence.entity.ProductEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProductRepository : JpaRepository<ProductEntity, UUID> {

    fun findAllByOrderByCreatedAtDesc(page: Pageable) : Page<ProductEntity>
    fun findByCategory(category: CategoryEntity, pageable: Pageable): Page<ProductEntity>
    @Query("SELECT p FROM ProductEntity p WHERE p.title LIKE %:keyword% OR p.description LIKE %:keyword%")
    fun findByTitleContainingOrDescriptionContaining(keyword : String): List<ProductEntity>
    fun findBySerialNumber(serialNumber: String): List<ProductEntity>
    @Query("SELECT p FROM ProductEntity p WHERE p.serialNumber = :serialNumber AND p.isSold = false")
    fun existsBySerialNumberNotSold(serialNumber: String): Boolean
    fun findByPriceBetween(min: Int, max: Int): List<ProductEntity>
}