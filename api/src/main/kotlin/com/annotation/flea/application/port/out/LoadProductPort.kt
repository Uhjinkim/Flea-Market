package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product

interface LoadProductPort {
    fun loadAllProducts(page: Int, criteria: String): List<Product>

    fun loadProductsBySerialNumber(page: Int, serialNumber: String): List<Product>

    fun loadProductsByCategory(page: Int, category: Category): List<Product>

}