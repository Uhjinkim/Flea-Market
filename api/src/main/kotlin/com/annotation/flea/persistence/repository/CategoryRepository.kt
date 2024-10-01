package com.annotation.flea.persistence.repository

import com.annotation.flea.persistence.entity.CategoryEntity
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
    fun findByName(name: String): CategoryEntity?
    fun findByNameAndParent(name: String, parent: CategoryEntity?): CategoryEntity?
    fun existsByName(name: String): Boolean
    fun existsByParent(parent: CategoryEntity): Boolean
}