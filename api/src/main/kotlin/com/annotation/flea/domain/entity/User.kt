package com.annotation.flea.domain.entity

import java.util.*

data class User(
    val id: UUID?,
    val username: String,
    val password: String,
    val name: String,
    val email: String,
    val nickname: String,
    val profileImage: String?,
    var address: Set<Address>? = setOf(),
    val phone: String,
    val role : Role,
) {
    data class Address(
        val id: UUID?,
        val userId: UUID,
        val street: String,
        val lot: String,
        val detail: String?) {
    }
}