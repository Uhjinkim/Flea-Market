package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.ProductEntity
import com.annotation.flea.persistence.entity.ProductImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<ProductImageEntity, String> {
}