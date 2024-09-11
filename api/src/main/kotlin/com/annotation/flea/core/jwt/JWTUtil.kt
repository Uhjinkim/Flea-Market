package com.annotation.flea.core.jwt

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Component
class JWTUtil(@Value("\${spring.jwt.secret}") secret : String) {

    val secretKey = SecretKeySpec(secret.toByteArray(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().algorithm)

    fun getUsername(token: String) : String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.get("username", String::class.java)
    }
    fun getEmail(token: String) : String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.get("email", String::class.java)
    }
    fun getName(token: String) : String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.get("name", String::class.java)
    }
    fun getPhone(token: String) : String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.get("phone", String::class.java)
    }
    fun getAddress(token: String) : String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.get("address", String::class.java)
    }
    fun getRole(token: String) : String {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.get("role", String::class.java)
    }
    fun isExpired(token: String) : Boolean {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload.expiration.before(Date())
    }
    fun createJwt(username: String,
                  email: String,
                  name: String,
                  phone: String,
                  address: String,
                  role: String,
                  expiredMs : Long) : String {
        return Jwts.builder()
            .claim("username", username)
            .claim("email", email)
            .claim("name", name)
            .claim("phone", phone)
            .claim("address", address)
            .claim("role", role)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiredMs))
            .signWith(secretKey)
            .compact()

    }
}