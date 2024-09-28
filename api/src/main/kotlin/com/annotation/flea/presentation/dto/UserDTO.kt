package com.annotation.flea.presentation.dto

data class UserDTO(
    val username: String,
    val password: String,
    val name: String,
    val email: String,
    val address: Address,
    val phone: String,
    val profileImage : String? = null,
) {
    data class Address(
        val street: String,
        val detail: String?
    )
}