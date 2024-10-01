package com.annotation.flea.mapper.`in`

import com.annotation.flea.domain.entity.Category
import com.annotation.flea.presentation.dto.CategoryResponse
import org.springframework.stereotype.Component

@Component
class CategoryDtoMapper {
    fun mapToDto(category: Category): CategoryResponse {
        return CategoryResponse(
            id = category.id,
            name = category.name,
            parent = category.parent?.name ?: "",
            depth = category.depth
        )
    }
}