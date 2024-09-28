package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.PutProductPort
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.mapper.out.CategoryMapper
import com.annotation.flea.mapper.out.ProductMapper
import com.annotation.flea.persistence.repository.CategoryRepository
import com.annotation.flea.persistence.repository.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class PutProductAdapter(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val productMapper: ProductMapper,
    private val categoryMapper: CategoryMapper
) : PutProductPort {
    override fun putProduct(product: Product): Boolean {
        if(!categoryRepository.existsByName(product.category.name)) {
            println("Category not found")
            return false
        }
        val category = categoryRepository.findByNameAndParent(product.category.name,
            product.category.parent?.let { categoryMapper.mapToCategoryEntity(it) })
        println(category)
        val entity = productMapper.mapToProductEntity(product)

        // 일련번호가 중복되는 상품이 있는지 확인, 있으면 거래 완료 여부 확인
        // 거래 진행 중인 상품일 경우 false 반환
        //로직 추가 예정

        productRepository.save(entity)
        return true
    }
}