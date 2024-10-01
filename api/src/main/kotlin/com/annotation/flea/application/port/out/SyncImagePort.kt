package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Image
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.domain.entity.User
import java.util.UUID

interface SyncImagePort {
    fun syncProductImage(product: Product, images: List<Image>)

    fun syncProfileImage(user: User, image: Image)
}