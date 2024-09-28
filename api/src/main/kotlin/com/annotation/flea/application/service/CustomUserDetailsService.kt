package com.annotation.flea.application.service

import com.annotation.flea.application.port.out.LoadUserPort
import com.annotation.flea.domain.entity.User
import com.annotation.flea.presentation.dto.CustomUserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val loadUserPort: LoadUserPort) : UserDetailsService {
    override fun loadUserByUsername(username: String): CustomUserDetails? {
        val userData : User? = loadUserPort.loadUserByUsername(username)
        if(userData != null) {
            val userDetails = CustomUserDetails(userData)
            return userDetails
        }
        return null
    }
}