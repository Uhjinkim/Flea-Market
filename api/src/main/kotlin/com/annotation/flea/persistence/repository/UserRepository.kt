package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun existsByUsername(username: String): Boolean
    fun findByUsername(username: String): UserEntity?
}