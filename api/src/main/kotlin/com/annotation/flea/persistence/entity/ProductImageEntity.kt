package com.annotation.flea.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_image_entity")
class ProductImageEntity(
    @ManyToOne
    @JoinColumn(name = "product_id")
    val product : ProductEntity,
    @Id
    val imageUrl : String,
) {
}