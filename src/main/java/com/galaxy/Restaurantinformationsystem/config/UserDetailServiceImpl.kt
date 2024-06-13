package com.galaxy.Restaurantinformationsystem.config

import com.galaxy.Restaurantinformationsystem.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(
    val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): org.springframework.security.core.userdetails.UserDetails {
        val userEntity = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        return org.springframework.security.core.userdetails.User.builder()
            .username(userEntity.email)
            .password(userEntity.password)
            .authorities(userEntity.role)
            .build()
    }
}
