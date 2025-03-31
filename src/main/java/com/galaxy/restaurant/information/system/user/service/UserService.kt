package com.galaxy.restaurant.information.system.user.service

import com.galaxy.restaurant.information.system.auth.controller.request.UserRegisterForm
import com.galaxy.restaurant.information.system.user.controller.response.UserResponse
import com.galaxy.restaurant.information.system.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @Transactional
    fun deleteUser(email: String) {
        val userEntity = userRepository.findByEmail(email) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        userRepository.deleteById(userEntity.id)
    }

    @Transactional
    fun updateUser(userDTO: UserRegisterForm, email: String): UserResponse {
        val userEntity = userRepository.findByEmail(email) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        userEntity.name = userDTO.name
        userEntity.password = passwordEncoder.encode(userDTO.password)
        return UserResponse.from(userRepository.save(userEntity))
    }

    @Transactional(readOnly = true)
    fun searchByEmail(email: String): UserResponse {
        val userEntity = userRepository.findByEmail(email) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        return UserResponse.from(userEntity)
    }
}
