package com.annotation.flea.persistence.entity

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
    @OneToMany(mappedBy = "parent")
    val subcategories: List<CategoryEntity> = listOf()
) {

}