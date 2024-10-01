package com.annotation.flea.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsMvcConfig(
    @Value("\${cors.allowed-origins}")private val allowedOrigins: String
) : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .exposedHeaders("Set-Cookie")
            .allowedOrigins("http://localhost:3000")
            .allowedOrigins(allowedOrigins)
    }
}