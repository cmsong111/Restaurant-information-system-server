package com.galaxy.Restaurantinformationsystem.config;

import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
import com.galaxy.Restaurantinformationsystem.common.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {


    final private UserDetailServiceCustom userDetailServiceCustom;
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;
    private Key key;


    @Autowired
    public JwtTokenProvider(UserDetailServiceCustom userDetailServiceCustom) {
        this.userDetailServiceCustom = userDetailServiceCustom;
    }

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Decoders.BASE64.decode(Base64.getEncoder().encodeToString(secretKey.getBytes()));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(UserEntity userEntity, UserRole userRoles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        // 쿠키 이름
        Claims claims = Jwts.claims().setSubject("내가 만든 쿠키~ 너를 위해 구웠지");

        // Spring Security HasRole 부분을 위한 부분
        claims.put("auth", userRoles.getAuthority());
        // 사용자 Id
        claims.put("uid", userEntity.getId());
        // 사용자 이메일
        claims.put("email", userEntity.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailServiceCustom.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUid(HttpServletRequest req) {
        String token = resolveToken(req);
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("uid", Long.class);
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

}
