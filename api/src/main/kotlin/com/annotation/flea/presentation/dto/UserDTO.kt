package com.annotation.flea.presentation.dto

import com.annotation.flea.domain.entity.Role
import java.util.UUID

data class UserDTO(
    val id: UUID? = null,
    val username: String,
    val password: String,
    val name: String,
    val email: String,
    val phone: String,
    val nickname: String,
    val role: Role? = null,
    val profileImage : String? = null,
) {

}