package com.annotation.flea.core.jwt

import com.annotation.flea.persistence.repository.RefreshTokenRepository
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.GenericFilterBean

class CustomLogoutFilter(
    private val jwtUtil: JWTUtil,
    private val refreshTokenRepository: RefreshTokenRepository
) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        doFilter(request as HttpServletRequest, response as HttpServletResponse, filterChain)
    }

    private fun doFilter(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        // if request URI is not /logout, do not filter
        val requestUri = request.requestURI
        if(requestUri != "/logout") {
            filterChain.doFilter(request, response)
            return
        }

        // if request method is not POST, do not filter
        val requestMethod = request.method
        if(requestMethod != "POST") {
            filterChain.doFilter(request, response)
            return
        }

        var refresh : String? = null
        val cookies = request.cookies
        if(cookies == null) {
            response.status = 400
            return
        }
        for(cookie in cookies) {
            if(cookie.name == "refresh") {
                refresh = cookie.value
            }
        }
        // if refresh token is not in the cookie, do not filter
        if(refresh == null) {
            response.status = 400
            return
        }

        // if refresh token is expired, do not filter
        try {
            jwtUtil.isExpired(refresh)
        } catch (e: ExpiredJwtException) {
            response.status = 400
            return
        }
        // if refresh token is invalid, do not filter
        val category = jwtUtil.getCategory(refresh)
        if(category != "refresh") {
            response.status = 400
            return
        }

        // if refresh token is not in the database, do not filter
        val isExist = refreshTokenRepository.existsByRefresh(refresh)
        if(!isExist) {
            response.status = 400
            return
        }
        // if refresh token is in the database, delete it
        refreshTokenRepository.deleteByRefresh(refresh)

        // delete refresh token from cookie
        val cookie = Cookie("refresh", null)
        cookie.maxAge = 0
        cookie.path = "/"
        response.addCookie(cookie)
        response.status = 200
    }
}