package com.annotation.flea.persistence.entity

import com.annotation.flea.domain.entity.Category
import com.annotation.flea.domain.entity.Image
import com.annotation.flea.domain.entity.Product
import com.annotation.flea.domain.entity.User
import com.fasterxml.uuid.Generators
import jakarta.persistence.*
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
    @Column(name = "category_id")
    var category: Long,

    // 상품 일련번호
    @Column(name = "serial_number")
    var serialNumber: String,

    // 판매자
    @Column(name = "username")
    val seller: String,

    // 거래 완료 여부
    @Column(name = "is_sold")
    var isSold: Boolean = false,

) : BaseTimeEntity() {
    fun mapToDomain(user: User, category: Category, images: List<Image>) : Product {
        return Product(
            id = id,
            title = title,
            description = description,
            price = price,
            category = category,
            serialNumber = serialNumber,
            seller = user,
            images = images,
            isSold = isSold,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

}