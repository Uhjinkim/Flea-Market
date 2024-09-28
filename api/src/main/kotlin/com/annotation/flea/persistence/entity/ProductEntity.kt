package com.annotation.flea.persistence.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "product_entity")
class ProductEntity(
    @Id
    @Column(name = "product_id")
    val id : UUID,

    var title: String,
    var description: String,
    var price: Int,
    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: CategoryEntity,

    var serialNumber: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val seller: UserEntity,

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    val buyer: UserEntity? = null,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: List<ProductImageEntity> = mutableListOf(),

    @CreationTimestamp
    val createdAt : LocalDateTime
) {
}