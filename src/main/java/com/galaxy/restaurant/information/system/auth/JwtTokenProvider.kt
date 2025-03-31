package com.galaxy.restaurant.information.system.auth

import com.galaxy.restaurant.information.system.user.entity.User
import com.galaxy.restaurant.information.system.user.entity.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.time.Instant
import java.util.Date
import java.util.UUID
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class JwtTokenProvider(
    private val userDetailsService: UserDetailsService,
    @Value("\${security.jwt.token.secret-key}")
    private var secretKey: String,
    @Value("\${security.jwt.token.expire-length}")
    private var expireLength: String
) {
    var key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun createToken(user: User): String {
        return Jwts.builder()
            .setId(UUID.randomUUID().toString())
            .setSubject(user.email)
            .setIssuer("Restaurant Information System")
            .claim("roles", user.roles)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(expireLength.toLong())))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val claims: Jws<Claims> = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)

        return UsernamePasswordAuthenticationToken(
            claims.body.subject,
            token,
            claims.body["roles", Collection::class.java]?.map {
                UserRole.valueOf(it as String)
            }?.toList()
        )
    }
}
