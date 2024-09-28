package com.annotation.flea.adapter.`in`

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController


@RestController
class AdminController {
    @GetMapping("/admin")
    fun adminP(): String {
        return "Admin Controller"
    }
}