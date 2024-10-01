package com.annotation.flea.persistence.entity

import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "product")
class ProductEntity(
    // 상품 고유 아이디
    @Id
    @Column(name = "product_id")
    val id : UUID = Generators.timeBasedEpochGenerator().generate(),

    // 상품명 혹은 제목
    var title: String,
    // 상품 설명
    var description: String,
    // 상품 가격
    var price: Int,

    // 상품 카테고리
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "category_id")
    var category: CategoryEntity,

    // 상품 일련번호
    @Column(name = "serial_number")
    var serialNumber: String,

    // 판매자
    @OneToOne
    @PrimaryKeyJoinColumn(name = "user_id")
    val seller: UserEntity,

    // 거래 완료 여부
    @Column(name = "is_sold")
    var isSold: Boolean = false,

    // 상품 이미지
    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: List<ProductImageEntity> = mutableListOf(),

    // 상품 등록 날짜
    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now(),

    // 상품 수정 날짜
    @CreationTimestamp
    @Column(name = "updated_at")
    val updatedAt : LocalDateTime = LocalDateTime.now(),
) {
}