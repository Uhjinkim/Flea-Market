package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.ProductImageEntity
import com.annotation.flea.persistence.entity.ProfileImageEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProductImageRepository : JpaRepository<ProductImageEntity, String> {
}
interface ProfileImageRepository : JpaRepository<ProfileImageEntity, String> {
}