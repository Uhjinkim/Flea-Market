package com.annotation.flea.presentation.dto

import java.time.LocalDateTime
import java.util.*


data class ProductDTO(
    val id: UUID?,
    val title: String,
    val description: String,
    val price: String,
    val categoryId: Long,
    val categoryName: String,
    val categoryDepth: String,
    val serialNumber: String,
    val images : List<String>,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
    val soldOut : String,
    val seller : String
) {

}