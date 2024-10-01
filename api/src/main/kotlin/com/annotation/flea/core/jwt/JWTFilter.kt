package com.annotation.flea.core.jwt

import com.annotation.flea.domain.entity.Role
import com.annotation.flea.presentation.dto.CustomUserDetails
import com.annotation.flea.domain.entity.User
import io.jsonwebtoken.ExpiredJwtException
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
        val accessToken = request.getHeader("Authorization")

        // if access token is null, do not filter
        if(accessToken == null) {
            filterChain.doFilter(request, response)
            return }

        // if access token is expired, do not filter
        try {
            jwtUtil.isExpired(accessToken)
        } catch (e: ExpiredJwtException) {
            val writer = response.writer
            writer.print("access token expired")
            return
        }
        // if access token is invalid, do not filter
        val category = jwtUtil.getCategory(accessToken)
        if(category != "access") {
            val writer = response.writer
            writer.print("invalid access token")
            response.status = 401
            return
        }

        val username = jwtUtil.getUsername(accessToken)
        val role = jwtUtil.getRole(accessToken)

        val user = User(
            id = null,
            username = username,
            password = "temppassword",
            email = "tempemail",
            name = "tempname",
            phone = "010-0000-0000",
            nickname = "tempnickname",
            address = emptySet(),
            role = if(role == Role.ROLE_ADMIN.name) Role.ROLE_ADMIN else Role.ROLE_MEMBER,
        )
        val customUserDetails = CustomUserDetails(user)

        val authToken = UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.authorities)
        SecurityContextHolder.getContext().authentication = authToken

        filterChain.doFilter(request, response)
    }

}