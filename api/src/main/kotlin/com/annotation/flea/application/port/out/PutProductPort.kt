package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product

interface PutProductPort {
    fun loadCategoryList() : List<Category>
    fun putProduct(product: Product) : Boolean
}