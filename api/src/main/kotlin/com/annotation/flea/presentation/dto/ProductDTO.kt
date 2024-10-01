package com.annotation.flea.presentation.dto

import java.util.*


data class ProductDTO(
    val id: UUID?,
    val title: String,
    val description: String,
    val price: String,
    val categoryId: Long,
    val categoryName: String,
    val serialNumber: String,
    val images : List<String>,
    val createdAt : String,
    val updatedAt : String,
    val soldOut : String,
    val seller : String
) {

}