package com.annotation.flea.domain.entity

data class Transaction(
    val customer: User,
    val seller: User,
    val goods: Goods,
    val price: Double,
) {
    val address = customer.address
}