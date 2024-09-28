package com.annotation.flea.mapper.out

import com.annotation.flea.domain.entity.Category
import com.annotation.flea.persistence.entity.CategoryEntity
import org.springframework.stereotype.Component

@Component
class CategoryMapper {
    fun mapToCategoryEntity(category: Category) :CategoryEntity {
        return CategoryEntity(
            id = category.id,
            name = category.name,
            parent = category.parent?.let {mapToCategoryEntity(it)},
        )
    }
    fun mapToCategoryDomain(entity: CategoryEntity) : Category{
        return Category(
            id = entity.id,
            name = entity.name,
            parent = entity.parent?.let {mapToCategoryDomain(it)},
        )
    }
}