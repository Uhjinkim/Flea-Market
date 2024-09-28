package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Product

interface DeleteProductPort {
    fun deleteProduct(product: Product) : Boolean
}