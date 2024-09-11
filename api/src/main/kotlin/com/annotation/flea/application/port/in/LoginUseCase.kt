package com.annotation.flea.application.port.`in`

interface LoginUseCase {
    fun login(username: String, password: String): Boolean
}