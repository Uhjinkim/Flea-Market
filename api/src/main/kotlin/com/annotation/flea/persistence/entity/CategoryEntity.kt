package com.annotation.flea.persistence.entity

import com.annotation.flea.domain.entity.Category
import jakarta.persistence.*

@Entity
@Table(name = "category")
class CategoryEntity(

    @Id
    @Column(name = "category_id")

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,

    val name : String,

    @ManyToOne
    val parent : CategoryEntity? = null,

    val depth : Int = 0,

) {
    fun mapToDomain() : Category {
        return Category(
            id = id,
            name = name,
            parent = parent?.mapToDomain(),
            depth = depth,
        )
    }
}