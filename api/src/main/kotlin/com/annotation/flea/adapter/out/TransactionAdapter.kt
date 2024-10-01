package com.annotation.flea.adapter.out

import com.annotation.flea.application.port.out.TransactionPort
import com.annotation.flea.domain.entity.Transaction
import java.time.LocalDateTime

class TransactionAdapter : TransactionPort {
    override fun comleteTransaction(transaction: Transaction) {
        transaction.transactionEnd = LocalDateTime.now()
        transaction.isActive = false

        val product = transaction.product
        product.isSold = true

    }
}