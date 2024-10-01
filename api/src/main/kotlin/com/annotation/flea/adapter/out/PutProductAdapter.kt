package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.PutProductPort
import com.annotation.flea.domain.entity.Category
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
    // 카테고리 목록을 불러오는 메서드
    override fun loadCategoryList(): List<Category> {
        val categoryList = categoryRepository.findAll()
        return categoryList.map {
            categoryMapper.mapToCategoryDomain(it)
        }
    }
    // 상품을 등록하는 메서드
    override fun putProduct(product: Product): Boolean {
        //카테고리 객체 매핑
        categoryMapper.mapToCategoryEntity(product.category)
        //상품 객체 매핑
        val entity = productMapper.mapToProductEntity(product)

        // 일련번호가 같은 상품 중에 거래 진행중인 상품이 있는지 확인 후 없으면 저장
        if (productRepository.existsBySerialNumberNotSold(entity.serialNumber))
            return false

        productRepository.save(entity)
        return true
    }
}