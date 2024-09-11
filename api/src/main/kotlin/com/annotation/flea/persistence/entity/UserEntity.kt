package com.annotation.flea.persistence.entity

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
    val id : UUID = UUID.randomUUID(),
    val username: String,
    val password: String,
    val email: String,
    val name : String,
    val phone: String,
    val role: String,
) {
}