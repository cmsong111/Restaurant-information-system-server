package com.galaxy.restaurant.information.system.config

import com.galaxy.restaurant.information.system.entity.UserEntity
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtTokenProvider(
    private val userDetailsService: UserDetailsService,
    @Value("\${security.jwt.token.secret-key}")
    private var secretKey: String,
    @Value("\${security.jwt.token.expire-length}")
    private var expireLength: String
) {

    var key: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)


    fun createToken(userEntity: UserEntity): String {
        val claims = Jwts.claims().setSubject(userEntity.email)
        claims["roles"] = userEntity.role
        val now = Date()
        val validity = Date(now.time + expireLength.toLong())
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    fun validateToken(token: String): Boolean {
        try {
            val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            return false
        }
    }
}
