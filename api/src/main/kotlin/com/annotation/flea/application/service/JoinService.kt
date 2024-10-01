package com.annotation.flea.application.service

import com.annotation.flea.application.port.out.CreateUserPort
import com.annotation.flea.domain.entity.Role
import com.annotation.flea.mapper.`in`.UserDtoMapper
import com.annotation.flea.presentation.dto.UserDTO
import org.springframework.stereotype.Service

@Service
class JoinService(
    private val createUserPort: CreateUserPort,
    private val mapper: UserDtoMapper,
) {
    fun join(userDTO: UserDTO) : Boolean {
        val data = mapper.mapToDomain(userDTO, Role.ROLE_MEMBER)
        if(createUserPort.createUser(data)) {
            println("회원가입 성공")
            return true
        } else {
            println("회원가입 실패")
            return false
        }
    }
}