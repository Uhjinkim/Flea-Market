package com.annotation.flea.domain.entity

import java.time.LocalDateTime
import java.util.*


data class Product(
    val id: UUID? = null,
    val title: String,
    val description: String,
    val price: Int,
    val images : List<String>,
    val category: Category,
    val serialNumber: String,
    val seller: User,
    val createdAt : LocalDateTime,
    var isSold: Boolean = false,
) {
}