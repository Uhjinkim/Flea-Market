package com.annotation.flea.domain.entity

import java.time.LocalDateTime
import java.util.*


data class Product(
    val id: UUID? = null,
    val title: String,
    val description: String,
    val price: Int,
    val images : List<Image>,
    val category: Category,
    val serialNumber: String,
    val seller: User,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
    var isSold: Boolean = false,
) {
}