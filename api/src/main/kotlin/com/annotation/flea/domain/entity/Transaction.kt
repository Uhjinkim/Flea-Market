package com.annotation.flea.domain.entity

import java.time.LocalDateTime

data class Transaction(
    val id: Long,
    val product: Product,
    val seller: User,
    val buyer: User,
    val transactionStart: LocalDateTime,
    var transactionEnd: LocalDateTime? = null,
    var isActive: Boolean = true,
) {
}