package com.annotation.flea.adapter.`in`

import com.annotation.flea.core.jwt.JWTUtil
import com.annotation.flea.persistence.entity.RefreshEntity
import com.annotation.flea.persistence.repository.RefreshTokenRepository
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
class ReissueController(
    private val jwtUtil: JWTUtil,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    @PostMapping("/reissue")
    fun reissue(
        request: HttpServletRequest,
        response: HttpServletResponse) : ResponseEntity<Any> {
        var refresh : String? = null
        val cookies = request.cookies ?: return ResponseEntity.badRequest().body("refresh token not found")
        for(cookie in cookies) {
            if(cookie.name == "refresh") {
                refresh = cookie.value
            }
        }
        // if refresh token is null, do not filter
        if(refresh == null) {
            return ResponseEntity.badRequest().body("refresh token not found")
        }

        // if refresh token is expired, do not filter
        try {
            jwtUtil.isExpired(refresh)
        } catch (e: ExpiredJwtException) {
            return ResponseEntity.badRequest().body("refresh token expired")
        }

        // if refresh token is invalid, do not filter
        val category = jwtUtil.getCategory(refresh)
        if(category != "refresh") {
            return ResponseEntity.badRequest().body("invalid refresh token")
        }

         // check if refresh token is in database and not expired
        val isExist = refreshTokenRepository.existsByRefresh(refresh)
        if(!isExist) {
            return ResponseEntity.badRequest().body("invalid refresh token")
        }

        val username = jwtUtil.getUsername(refresh)
        val role = jwtUtil.getRole(refresh)

        // reissue access token
        val newAccess = jwtUtil.createJwt(
            category = "access",
            username = username,
            role = role,
            expiredMs = 60000L)
        // reissue refresh token
        val newRefresh = jwtUtil.createJwt(
            category = "refresh",
            username = username,
            role = role,
            expiredMs = 86400000L)

        // delete old refresh token and add new refresh token
        refreshTokenRepository.deleteByRefresh(refresh)
        addRefreshEntity(username, newRefresh, 86400000L)

        // set new access token and refresh token
        response.setHeader("Authorization", newAccess)
        response.addCookie(createCookie("refresh", newRefresh))
        return ResponseEntity.ok("access token reissued")
    }
    private fun createCookie(key: String, value: String) : Cookie {
        val cookie = Cookie(key, value)
        cookie.isHttpOnly = true
        cookie.maxAge = 24*60*60
        return cookie
    }
    private fun addRefreshEntity(username: String, refresh: String, expiredMs: Long) {
        val date = Date(System.currentTimeMillis() + expiredMs)
        val entity = RefreshEntity(
            username = username,
            refresh = refresh,
            expiredAt = date.toString()
        )
        refreshTokenRepository.save(entity)
    }
}