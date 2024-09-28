package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.Transaction

interface TransactionPort {
    fun comleteTransaction(transaction: Transaction)
}