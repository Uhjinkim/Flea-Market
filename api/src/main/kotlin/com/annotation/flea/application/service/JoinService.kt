package com.annotation.flea.application.service

import com.annotation.flea.application.dto.JoinDTO
import com.annotation.flea.application.port.out.CreateUserPort
import com.annotation.flea.domain.entity.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class JoinService(
    private val createUserPort: CreateUserPort,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) {
    fun joinProcess(joinDTO: JoinDTO) {
        val username = joinDTO.username
        val password = joinDTO.password
        val name = joinDTO.name
        val email = joinDTO.email
        val phone = joinDTO.phone
        val addressStreet = joinDTO.addressStreet
        val addressDetail = joinDTO.addressDetail
        val data = User(
            username = username,
            password = bCryptPasswordEncoder.encode(password),
            email = email,
            name = name,
            address = User.Address(street = "20 W 34th St.", detail = "Empire State Building"),
            phone = User.Phone(former = phone.substring(3, 7), latter = phone.substring(7)),
            role = "ROLE_ADMIN"
        )
        if(createUserPort.createUser(data)) {
            println("회원가입 성공")
        } else {
            println("회원가입 실패")
        }
    }
}