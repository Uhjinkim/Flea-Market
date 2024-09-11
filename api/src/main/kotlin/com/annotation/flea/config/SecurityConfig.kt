package com.annotation.flea.config

import com.annotation.flea.core.jwt.JWTFilter
import com.annotation.flea.core.jwt.JWTUtil
import com.annotation.flea.core.jwt.LoginFilter
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
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val authenticationConfiguration: AuthenticationConfiguration,
    val jwtUtil: JWTUtil
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
                configuration.allowedOrigins = listOf("http://localhost:3000")
                configuration.allowedMethods = listOf("*")
                configuration.allowCredentials = true
                configuration.allowedHeaders = listOf("*")
                configuration.maxAge = 3600L
                configuration.exposedHeaders = listOf("Authorization")
                configuration
            }
            }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests { it
                    .requestMatchers("/login", "/", "/join").permitAll()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                JWTFilter(jwtUtil),
                LoginFilter::class.java
            )
            .addFilterAt(
                LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil),
                UsernamePasswordAuthenticationFilter::class.java
                )
            .sessionManagement { it
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        return http.build()
    }
}