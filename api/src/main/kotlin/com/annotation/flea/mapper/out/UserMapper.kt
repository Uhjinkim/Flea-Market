package com.annotation.flea.mapper.out

import com.annotation.flea.domain.entity.Role
import com.annotation.flea.domain.entity.User
import com.annotation.flea.persistence.entity.ProfileImageEntity
import com.annotation.flea.persistence.entity.UserAddressEntity
import com.annotation.flea.persistence.entity.UserEntity
import com.fasterxml.uuid.Generators
import org.springframework.stereotype.Component

// 도메인 객체 User, 영속성 엔티티 UserEntity 간의 매핑을 담당하는 UserMapper 클래스
@Component
class UserMapper(
) {
    fun mapToUserEntity(user: User): UserEntity {
        val entity =  UserEntity(
            id = user.id?: Generators.timeBasedEpochGenerator().generate(),
            username = user.username,
            password = user.password,
            email = user.email,
            name = user.name,
            phone = user.phone,
            nickname = user.nickname,
            role = if(user.role == Role.ROLE_ADMIN) Role.ROLE_ADMIN.name
                else Role.ROLE_MEMBER.name
        )
        return entity
    }
}