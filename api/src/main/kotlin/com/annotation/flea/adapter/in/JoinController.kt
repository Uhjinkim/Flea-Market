package com.annotation.flea.adapter.`in`

import com.annotation.flea.application.dto.JoinDTO
import com.annotation.flea.application.service.JoinService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@ResponseBody
class JoinController(
    private val joinService: JoinService) {

    @PostMapping("/join")
    fun joinP(joinDTO: JoinDTO): String {
        println("$joinDTO.username 회원가입 요청")
        joinService.joinProcess(joinDTO)

        return "ok"
    }

}