package com.annotation.flea.adapter.out

import com.annotation.flea.mapper.out.ProductImageMapper
import com.annotation.flea.mapper.out.ProfileImageMapper
import com.annotation.flea.persistence.entity.ProductEntity
import com.annotation.flea.persistence.repository.ProductImageRepository
import com.annotation.flea.persistence.repository.ProfileImageRepository
import org.springframework.stereotype.Repository

@Repository
class ImageAdapter(
    private val productImageMapper: ProductImageMapper,
    private val profileImageMapper: ProfileImageMapper,
    private val productImageRepository: ProductImageRepository,
    private val profileImageRepository: ProfileImageRepository,
) {
    fun syncProductImage(product: ProductEntity, imageUrls: List<String>) {
        val productImages = product.images.toMutableList()
        val newImages = imageUrls.toMutableList()
        val imagesToDelete = mutableListOf<String>()

        productImages.forEach {
            if(it.imageUrl !in newImages) {
                imagesToDelete.add(it.imageUrl)
            } else {
                newImages.remove(it.imageUrl)
            }
        }
        imagesToDelete.forEach {
            productImageRepository.deleteById(it)
        }
        newImages.forEach {
            val productImageEntity = productImageMapper.mapToImageEntity(it, product)
            productImageRepository.save(productImageEntity)
            productImages.add(productImageEntity)
        }
        product.images = productImages

    }
}