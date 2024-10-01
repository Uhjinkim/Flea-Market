package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.LoadImagePort
import com.annotation.flea.application.port.out.SyncImagePort
import com.annotation.flea.domain.entity.Image
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.domain.entity.User
import com.annotation.flea.mapper.out.ProductImageMapper
import com.annotation.flea.mapper.out.ProductMapper
import com.annotation.flea.mapper.out.ProfileImageMapper
import com.annotation.flea.mapper.out.UserMapper
import com.annotation.flea.persistence.repository.ProductImageRepository
import com.annotation.flea.persistence.repository.ProductRepository
import com.annotation.flea.persistence.repository.ProfileImageRepository
import com.annotation.flea.persistence.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class ImageAdapter(
    private val productImageMapper: ProductImageMapper,
    private val profileImageMapper: ProfileImageMapper,
    private val productMapper: ProductMapper,
    private val userMapper: UserMapper,

    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val productImageRepository: ProductImageRepository,
    private val profileImageRepository: ProfileImageRepository,
) : SyncImagePort, LoadImagePort {
    override fun syncProductImage(product: Product, images: List<Image>) {
        val productImages = product.images.toMutableList()
        val newImages = images.toMutableList()
        val imagesToDelete = mutableListOf<String>()
        val productEntity = productMapper.mapToProductEntity(product)
        if(!productRepository.existsById(productEntity.id)) {
            productImageRepository.deleteByProduct(productEntity.id)
            return
        }
        productImages.forEach {
            if(it !in newImages) {
                imagesToDelete.add(it.imageUrl)
            } else {
                newImages.remove(it)
            }
        }
        imagesToDelete.forEach {
            productImageRepository.deleteByImageUrl(it)
        }
        newImages.forEach {
            val productImageEntity = productImageMapper.mapToImageEntity(it, productEntity.id)
            productImageRepository.save(productImageEntity)
        }
    }

    override fun syncProfileImage(user: User, image: Image) {
        val userEntity = userMapper.mapToUserEntity(user)
        val profileImageEntity = profileImageMapper.mapToImageEntity(image, userEntity.id)
        profileImageRepository.save(profileImageEntity)
    }

    override fun loadProductImage(product: Product): List<Image> {
        val productEntity = productMapper.mapToProductEntity(product)

        val user = userRepository.findByUsername(productEntity.seller)?.mapToDomain() ?: return emptyList()
        val imageList = productImageRepository.findByProduct(productEntity.id)
        val images = imageList.map {
            it.mapToDomain(user)
        }
        return images
    }
}