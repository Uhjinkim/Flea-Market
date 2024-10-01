package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.ProductEntity
import com.annotation.flea.persistence.entity.ProductImageEntity
import com.annotation.flea.persistence.entity.ProfileImageEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductImageRepository : JpaRepository<ProductImageEntity, Long> {
    fun existsByImageUrl(imageUrl: String): Boolean
    fun findByProduct(productId : UUID): List<ProductImageEntity>
    fun deleteByImageUrl(imageUrl: String)
    fun deleteByProduct(productId : UUID)
}
interface ProfileImageRepository : JpaRepository<ProfileImageEntity, Long> {
}