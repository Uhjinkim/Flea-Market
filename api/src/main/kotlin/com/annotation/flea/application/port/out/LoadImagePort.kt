package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Image
import com.annotation.flea.domain.entity.Product

interface LoadImagePort {
    fun loadProductImage(product: Product) : List<Image>
}