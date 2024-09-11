package com.annotation.flea.persistence.mapper.out

import com.annotation.flea.domain.entity.User
import com.annotation.flea.persistence.entity.UserEntity
import org.springframework.stereotype.Component

@Component
class UserMapper(
) {
    fun mapToUserEntity(user: User): UserEntity {
        return UserEntity(
            username = user.username,
            password = user.password,
            email = user.email,
            name = user.name,
            phone = "010${user.phone.former}${user.phone.latter}",
            role = user.role
        )
    }
    fun mapToUser(entity: UserEntity): User {
        val phone = entity.phone
        return User(
            username = entity.username,
            password = entity.password,
            email = entity.email,
            name = entity.name,
            address = User.Address("20 W 34th St.", "Empire State Building"),
            phone = User.Phone(phone.substring(3, 7), phone.substring(7)),
            role = entity.role
        )
    }
}