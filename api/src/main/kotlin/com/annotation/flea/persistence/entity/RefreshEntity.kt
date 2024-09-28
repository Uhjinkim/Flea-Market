package com.annotation.flea.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "refresh_token")
class RefreshEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val username: String,
    val refresh : String,
    val expiredAt: String,
) {

}