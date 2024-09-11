package com.annotation.flea.application.port.out

import com.annotation.flea.domain.entity.User

interface CreateUserPort {
    fun createUser(user: User): Boolean
}