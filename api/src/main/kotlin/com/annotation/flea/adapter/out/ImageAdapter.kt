package com.annotation.flea.adapter.out

import com.annotation.flea.domain.entity.Product
import com.annotation.flea.persistence.entity.ProductEntity
import com.annotation.flea.mapper.out.ImageMapper
import com.annotation.flea.persistence.repository.ImageRepository
import org.springframework.stereotype.Repository

@Repository
class ImageAdapter(
    private val imageMapper: ImageMapper,
    private val imageRepository: ImageRepository,
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
            imageRepository.deleteById(it)
        }
        newImages.forEach {
            val productImageEntity = imageMapper.mapToImageEntity(it, product)
            imageRepository.save(productImageEntity)
            productImages.add(productImageEntity)
        }
        product.images = productImages

    }
}