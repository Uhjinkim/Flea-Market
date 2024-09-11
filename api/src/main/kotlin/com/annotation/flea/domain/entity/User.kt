package com.annotation.flea.domain.entity

data class User(
    val name: String,
    val email: String,
    val address: Address?,
    val username: String,
    val password: String,
    val phone: Phone,
    val role : String) {
    class Phone(val former : String, val latter : String) {
    }
    class Address(val street: String, val detail: String?) {
    }
}