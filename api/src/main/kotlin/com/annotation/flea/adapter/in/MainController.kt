package com.annotation.flea.adapter.`in`

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@ResponseBody
class MainController {
    @GetMapping("/")
    fun mainP(): String {
        val name = SecurityContextHolder.getContext().authentication.name
        val authentication = SecurityContextHolder.getContext().authentication
        val authorities = authentication.authorities
        val iter = authorities.iterator()
        val auth = iter.next();
        val role = auth.authority
        return "Dmart: Hello, $name!. Your role is $role"
    }
}