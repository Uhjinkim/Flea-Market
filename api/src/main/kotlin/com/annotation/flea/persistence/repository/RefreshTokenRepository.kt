package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.RefreshEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshEntity, Long> {
    fun existsByRefresh(refresh: String) : Boolean

    @Transactional
    fun deleteByRefresh(refresh: String)
}