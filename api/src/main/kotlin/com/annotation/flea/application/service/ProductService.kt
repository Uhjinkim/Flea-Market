package com.annotation.flea.application.service

import com.annotation.flea.application.port.out.EditProductPort
import com.annotation.flea.application.port.out.LoadProductPort
import com.annotation.flea.application.port.out.PutProductPort
import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Product
import org.hibernate.query.criteria.JpaCteCriteria
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val loadProductPort: LoadProductPort,
    private val putProductPort: PutProductPort,
    private val editProductPort: EditProductPort,
) {
    fun getProducts(page: Int, criteria: String, category: Category?) : List<Product>? {
        if(category == null) return loadProductPort.loadAllProducts(page, criteria)
        else return loadProductPort.loadProductsByCategory(page, category)
    }
    fun getCategoryList() : List<Category> {
        return putProductPort.loadCategoryList()
    }
    fun sellProduct(product: Product) : Boolean {
        return putProductPort.putProduct(product)
    }
    fun editProduct(product: Product) : Boolean {
        return editProductPort.editProduct(product)
    }
}