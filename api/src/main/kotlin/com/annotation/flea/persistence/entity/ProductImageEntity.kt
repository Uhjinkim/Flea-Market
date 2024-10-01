package com.annotation.flea.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "product_image")
class ProductImageEntity(
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    val product : ProductEntity,

    @Id
    val imageUrl : String,

    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt : LocalDateTime = LocalDateTime.now(),
) {
}