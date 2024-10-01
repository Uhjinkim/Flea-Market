package com.annotation.flea.adapter.`in`

import com.annotation.flea.application.service.JoinService
import com.annotation.flea.presentation.dto.UserDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JoinController(
    private val joinService: JoinService) {


    @PostMapping("/join")
    @ResponseBody
    fun joinP(userDTO: UserDTO): String {
        println("${userDTO.username} 회원가입 요청")
        if(joinService.join(userDTO)) {
            return "ok"
        }
        return "no"
    }

}