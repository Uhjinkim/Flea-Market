package com.annotation.flea.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "transaction")
class TransactionEntity(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),
    @ManyToOne
    val product: ProductEntity,
    @ManyToOne
    val seller: UserEntity,
    @ManyToOne
    val buyer: UserEntity,
    val transactionStart: LocalDateTime,
    val transactionEnd: LocalDateTime? = null,
    val isActive: Boolean = true,
) {
}