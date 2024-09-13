package com.annotation.flea.domain.entity

data class User(
    val username: String,
    val password: String,
    val name: String,
    val email: String,
    val address: Address,
    val phone: Phone,
    val role : String,
    val products : List<Product> = listOf(),
    val transactions : List<Transaction> = listOf()
) {
    class Phone(val former : String, val latter : String) {
    }
    class Address(val street: String, val detail: String?) {
    }
}