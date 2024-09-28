package com.annotation.flea.application.service

import com.annotation.flea.application.port.out.EditProductPort
import com.annotation.flea.application.port.out.LoadProductPort
import com.annotation.flea.application.port.out.PutProductPort
import com.annotation.flea.domain.entity.Product
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val loadProductPort: LoadProductPort,
    private val putProductPort: PutProductPort,
    private val editProductPort: EditProductPort,
) {
    fun getProducts() : List<Product>? {
        return loadProductPort.loadProductsByPriceRange(0, 1000000)
    }
    fun sellProduct(product: Product) : Boolean {
        return putProductPort.putProduct(product)
    }
    fun editProduct(product: Product) : Boolean {
        return editProductPort.editProduct(product)
    }
}