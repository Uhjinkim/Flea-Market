package com.annotation.flea.mapper.`in`

import com.annotation.flea.domain.entity.User
import com.annotation.flea.presentation.dto.UserDTO
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserDtoMapper(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) {
    fun mapToDomain(userDTO: UserDTO): User {
        return User(
            id = userDTO.id,
            username = userDTO.username,
            password = encodePassword(userDTO.password),
            name = userDTO.name,
            email = userDTO.email,
            phone = userDTO.phone,
            nickname = userDTO.nickname,
            role = userDTO.role,
        )
    }
    fun encodePassword(password: String): String {
        return bCryptPasswordEncoder.encode(password)
    }
}