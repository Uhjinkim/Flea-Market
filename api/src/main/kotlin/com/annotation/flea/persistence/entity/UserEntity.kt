package com.annotation.flea.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name="user_entity")
class UserEntity(
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    val id : UUID = UUID.randomUUID(),
    val username: String,
    val password: String,
    val email: String,
    val name : String,
    @Embedded
    val address: Address,
    val phone: String,
    val role: String,

) {
    @Embeddable
    data class Address(
        val street: String,
        val detail: String?
    )
}