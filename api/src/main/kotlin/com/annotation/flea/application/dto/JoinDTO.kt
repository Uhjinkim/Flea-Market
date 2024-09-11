package com.annotation.flea.application.dto

data class JoinDTO(
    val username: String,
    val password: String,
    val name: String,
    val email: String,
    val phone: String,
    val addressStreet: String,
    val addressDetail: String,

    ) {
}