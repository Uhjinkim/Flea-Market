package com.annotation.flea.mapper.out

import com.annotation.flea.domain.entity.User
import com.annotation.flea.persistence.entity.UserEntity
import org.springframework.stereotype.Component

// 도메인 객체 User, 영속성 엔티티 UserEntity 간의 매핑을 담당하는 UserMapper 클래스
@Component
class UserMapper(
) {
    fun mapToUserEntity(user: User): UserEntity {
        return UserEntity(
            username = user.username,
            password = user.password,
            email = user.email,
            name = user.name,
            phone = user.phone,
            address = UserEntity.Address(user.address.street, user.address.detail),
            role = user.role
        )
    }
    fun mapToUserDomain(entity: UserEntity): User {
        return User(
            username = entity.username,
            password = entity.password,
            email = entity.email,
            name = entity.name,
            address = User.Address(entity.address.street, entity.address.detail),
            phone = entity.phone,
            role = entity.role
        )
    }
}