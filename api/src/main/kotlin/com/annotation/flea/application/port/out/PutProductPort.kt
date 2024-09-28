package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Product

interface PutProductPort {
    fun putProduct(product: Product) : Boolean
}