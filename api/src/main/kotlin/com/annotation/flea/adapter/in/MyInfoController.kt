package com.annotation.flea.adapter.`in`

import com.annotation.flea.application.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class MyInfoController(
    private val userService: UserService // MyInfoService 의 bean을 주입합니다.
) {
    @GetMapping("/info")
    fun info(@RequestHeader("Authorization") token : String) : String {
        userService.loadUserByToken(token)
        return "info"
    }
}