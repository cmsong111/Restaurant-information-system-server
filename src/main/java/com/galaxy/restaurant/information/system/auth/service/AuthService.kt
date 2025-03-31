package com.galaxy.restaurant.information.system.auth.service

import com.galaxy.restaurant.information.system.auth.JwtTokenProvider
import com.galaxy.restaurant.information.system.auth.controller.request.UserRegisterForm
import com.galaxy.restaurant.information.system.user.entity.User
import com.galaxy.restaurant.information.system.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun registerUser(userRegisterForm: UserRegisterForm): String {
        if (userRepository.existsByEmail(userRegisterForm.email)) {
            throw RuntimeException("이미 가입되어 있는 유저입니다.")
        }

        val user: User = User(
            email = userRegisterForm.email,
            name = userRegisterForm.name,
            password = passwordEncoder.encode(userRegisterForm.password)
        )

        return jwtTokenProvider.createToken(
            userRepository.save(user)
        )
    }

    @Transactional(readOnly = true)
    fun login(
        email: String,
        password: String,
    ): String {
        val user: User = userRepository.findByEmail(email)
            ?: throw RuntimeException("가입되지 않은 유저입니다.")

        if (!passwordEncoder.matches(password, user.password)) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }

        return jwtTokenProvider.createToken(user)
    }

    @Transactional(readOnly = true)
    fun isAvailableEmail(email: String): Boolean {
        return !userRepository.existsByEmail(email)
    }
}
