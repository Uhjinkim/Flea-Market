package com.annotation.flea.presentation.dto

data class CategoryResponse(
    val id: Long,
    val name: String,
    val parent: String,
    val depth: Int,
)