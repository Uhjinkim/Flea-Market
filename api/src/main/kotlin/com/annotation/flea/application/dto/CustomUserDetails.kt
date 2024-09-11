package com.annotation.flea.application.dto

import com.annotation.flea.domain.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val user: User
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        val collection = mutableListOf<GrantedAuthority>()

        collection.add(GrantedAuthority {
            user.role
        })
        return collection
    }
    override fun getPassword(): String {
        return user.password
    }
    override fun getUsername(): String {
        return user.username
    }
    override fun isAccountNonExpired(): Boolean {
        return true
    }
    override fun isAccountNonLocked(): Boolean {
        return true
    }
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
    override fun isEnabled(): Boolean {
        return true
    }
}