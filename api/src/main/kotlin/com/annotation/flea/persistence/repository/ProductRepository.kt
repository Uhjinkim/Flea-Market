package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.CategoryEntity
import com.annotation.flea.persistence.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface ProductRepository : JpaRepository<ProductEntity, UUID> {
    @Query("SELECT p FROM ProductEntity p WHERE p.title LIKE %:keyword% OR p.description LIKE %:keyword%")
    fun findByTitleContainingOrDescriptionContaining(keyword : String): List<ProductEntity>
    fun findByCategory(category: CategoryEntity): List<ProductEntity>
    fun findBySerialNumber(serialNumber: String): List<ProductEntity>
    fun findByPriceBetween(min: Int, max: Int): List<ProductEntity>
}