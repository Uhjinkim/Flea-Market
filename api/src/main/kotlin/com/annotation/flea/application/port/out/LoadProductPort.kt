package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product

interface LoadProductPort {
    fun loadProductsBySerialNumber(serialNumber: String): List<Product>?

    fun loadProductsByCategory(category: Category): List<Product>?

    fun loadProductsByPriceRange(minPrice: Int, maxPrice: Int): List<Product>?


}