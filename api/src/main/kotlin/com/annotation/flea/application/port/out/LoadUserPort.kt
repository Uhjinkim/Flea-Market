package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.User


interface LoadUserPort {
    fun loadUserByUsername(username: String): User?
}