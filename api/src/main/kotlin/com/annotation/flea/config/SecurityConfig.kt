package com.annotation.flea.config

import com.annotation.flea.core.jwt.CustomLogoutFilter
import com.annotation.flea.core.jwt.JWTFilter
import com.annotation.flea.core.jwt.JWTUtil
import com.annotation.flea.core.jwt.LoginFilter
import com.annotation.flea.persistence.repository.RefreshTokenRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutFilter
import org.springframework.web.cors.CorsConfiguration
import java.util.*

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val jwtUtil: JWTUtil,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration) : AuthenticationManager {
        return configuration.authenticationManager
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http
            .cors { it.configurationSource {
                val configuration = CorsConfiguration()
                configuration.allowedOrigins = Collections.singletonList("http://localhost:3000")
                configuration.allowedMethods = Collections.singletonList("*")
                configuration.allowCredentials = true
                configuration.allowedHeaders = Collections.singletonList("*")
                configuration.maxAge = 3600L
                configuration.exposedHeaders = Collections.singletonList("Authorization")
                configuration
            }
            }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests { it
                    .requestMatchers("/login", "/", "/join").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/reissue").permitAll()
                    .requestMatchers("/error").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                JWTFilter(jwtUtil),
                LoginFilter::class.java
            )
            .addFilterAt(
                LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshTokenRepository),
                UsernamePasswordAuthenticationFilter::class.java
                )
            .addFilterBefore(
                CustomLogoutFilter(jwtUtil, refreshTokenRepository),
                LogoutFilter::class.java
            )
            .sessionManagement { it
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        return http.build()
    }

}