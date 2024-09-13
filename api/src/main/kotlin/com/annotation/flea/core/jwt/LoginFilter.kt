package com.annotation.flea.core.jwt

import com.annotation.flea.application.dto.CustomUserDetails
import com.annotation.flea.persistence.entity.RefreshEntity
import com.annotation.flea.persistence.repository.RefreshTokenRepository
import io.jsonwebtoken.io.IOException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*

class LoginFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
    private val refreshTokenRepository: RefreshTokenRepository,
) : UsernamePasswordAuthenticationFilter() {
    @Throws(IOException::class, ServletException::class)
    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ): Authentication {
        val username = obtainUsername(request)
        val password = obtainPassword(request)

        println("Attempting authentication for user: $username")

        val authToken = UsernamePasswordAuthenticationToken(username, password, null)

        return authenticationManager.authenticate(authToken)
    }
    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authentication: Authentication
    ) {
        val username = authentication.name

        val authorities = authentication.authorities
        val iter = authorities.iterator()
        val auth = iter.next()
        val role = auth.authority

        val access = jwtUtil.createJwt(
            category = "access",
            username = username,
            role = role,
            expiredMs = 60000L)

        val refresh = jwtUtil.createJwt(
            category = "refresh",
            username = username,
            role = role,
            expiredMs = 86400000L)

        // add refresh token to database
        addRefreshEntity(username, refresh, 86400000L)

        // add access token to header and refresh token to cookie
        response.setHeader("access", access)
        response.addCookie(createCookie("refresh", refresh))
        response.status = 200
    }
    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException) {
        response.status = 401
    }

    private fun createCookie(key: String, value: String) : Cookie {
        val cookie = Cookie(key, value)
        cookie.maxAge = 24*60*60
        cookie.isHttpOnly = true
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