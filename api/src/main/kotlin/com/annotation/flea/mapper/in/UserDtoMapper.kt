package com.annotation.flea.mapper.`in`

import com.annotation.flea.domain.entity.Role
import com.annotation.flea.domain.entity.User
import com.annotation.flea.presentation.dto.UserDTO
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserDtoMapper(
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) {
    fun mapToUser(userDTO: UserDTO, role: Role): User {
        return User(
            username = userDTO.username,
            password = encodePassword(userDTO.password),
            name = userDTO.name,
            email = userDTO.email,
            phone = userDTO.phone,
            address = User.Address(
                street = userDTO.address.street,
                detail = userDTO.address.detail
            ),
            role = role.name
        )
    }
    fun encodePassword(password: String): String {
        return bCryptPasswordEncoder.encode(password)
    }
}