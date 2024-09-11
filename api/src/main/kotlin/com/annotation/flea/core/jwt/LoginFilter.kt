package com.annotation.flea.core.jwt

import com.annotation.flea.application.dto.CustomUserDetails
import io.jsonwebtoken.io.IOException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class LoginFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtil: JWTUtil,
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
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authentication: Authentication?
    ) {
        val customUserDetails = authentication?.principal as CustomUserDetails
        val user = customUserDetails.user

        val authorities = authentication.authorities
        val iterator = authorities.iterator()
        val auth = iterator.next()

        val role = auth.authority

        val token = jwtUtil.createJwt(
            username = user.username,
            name = user.name,
            email = user.email,
            phone = "010${user.phone.former}${user.phone.latter}",
            address = "${user.address?.street}, ${user.address?.detail}",
            role = role,
            expiredMs = 60*60*10L)

        response?.addHeader("Authorization", "Bearer $token")
    }
    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException) {
        response.status = 401
    }
}