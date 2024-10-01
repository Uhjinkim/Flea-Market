package com.annotation.flea.domain.entity

data class Category(
    val id: Long,
    val name: String,
    val parent: Category? = null,
    val depth : Int,
) {
}