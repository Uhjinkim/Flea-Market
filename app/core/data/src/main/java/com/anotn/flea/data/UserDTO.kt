package com.anotn.flea.data

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val username : String,
    val password : String,
)