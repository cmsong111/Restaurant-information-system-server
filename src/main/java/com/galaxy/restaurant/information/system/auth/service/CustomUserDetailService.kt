package com.galaxy.restaurant.information.system.auth.service

import com.galaxy.restaurant.information.system.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): org.springframework.security.core.userdetails.UserDetails {
        val userEntity = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        return org.springframework.security.core.userdetails.User.builder()
            .username(userEntity.email)
            .password(userEntity.password)
            .authorities(userEntity.roles)
            .build()
    }
}
