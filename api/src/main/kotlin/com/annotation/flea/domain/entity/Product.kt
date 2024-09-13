package com.annotation.flea.domain.entity

import java.time.LocalDateTime
import java.util.*


data class Product(
    val id: UUID,
    val title: String,
    val description: String,
    val price: Int,
    val images : List<String>,
    val category: Category,
    val serialNumber: String,
    val seller: User,
    var buyer: User? = null,
    val createdAt : LocalDateTime,
    var isSold: Boolean = false,
) {
}