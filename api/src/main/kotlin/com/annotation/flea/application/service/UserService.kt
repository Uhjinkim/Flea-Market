package com.annotation.flea.application.service

import com.annotation.flea.application.port.out.LoadUserPort
import com.annotation.flea.domain.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val loadUserPort: LoadUserPort
) {
    fun loadUserByUsername(username: String): User? {
        return loadUserPort.loadUserByUsername(username)
    }
}