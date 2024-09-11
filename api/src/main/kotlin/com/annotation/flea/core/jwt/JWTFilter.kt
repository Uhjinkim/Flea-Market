package com.annotation.flea.core.jwt

import com.annotation.flea.application.dto.CustomUserDetails
import com.annotation.flea.domain.entity.User
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader("Authorization")
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            println("token null")
            filterChain.doFilter(request, response)
            return
        }
        println("authorization now")
        val token = authorization.split(" ")[1]
        if (jwtUtil.isExpired(token)) {
            println("token expired")
            filterChain.doFilter(request, response)
            return
        }
        val username = jwtUtil.getUsername(token)
        val role = jwtUtil.getRole(token)
        val email = jwtUtil.getEmail(token)
        val name = jwtUtil.getName(token)
        val phone = jwtUtil.getPhone(token)
        val address = jwtUtil.getAddress(token)

        val user = User(
            username = username,
            password = "temppassword",
            email = email,
            name = name,
            address = User.Address("20 W 34th St.", "Empire State Building"),
            phone = User.Phone(phone.substring(3, 7), phone.substring(7)),
            role = role
        )
        val customUserDetails = CustomUserDetails(user)

        val authToken = UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.authorities)
        SecurityContextHolder.getContext().authentication = authToken

        filterChain.doFilter(request, response)
    }

}