package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Product

interface EditProductPort {
    fun editProduct(product: Product) : Boolean
}