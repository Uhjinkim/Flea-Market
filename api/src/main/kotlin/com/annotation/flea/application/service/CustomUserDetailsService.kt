package com.annotation.flea.application.service

import com.annotation.flea.application.dto.CustomUserDetails
import com.annotation.flea.application.port.out.LoadUserPort
import com.annotation.flea.domain.entity.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val loadUserPort: LoadUserPort) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val userData : User? = loadUserPort.loadUserByUsername(username)
        if(userData != null) {
            return CustomUserDetails(userData)
        }
        return null
    }
}