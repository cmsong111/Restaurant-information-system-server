package com.galaxy.restaurant.information.system.service

import com.galaxy.restaurant.information.system.config.JwtTokenProvider
import com.galaxy.restaurant.information.system.dto.UserInfoDto
import com.galaxy.restaurant.information.system.dto.UserRegisterFormDto
import com.galaxy.restaurant.information.system.entity.UserEntity
import com.galaxy.restaurant.information.system.mapper.UserMapper
import com.galaxy.restaurant.information.system.repository.ReviewRepository
import com.galaxy.restaurant.information.system.repository.StoreRepository
import com.galaxy.restaurant.information.system.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository,
    private val reviewRepository: ReviewRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val userMapper: UserMapper
) {
    fun registerUser(userRegisterFormDto: UserRegisterFormDto): UserInfoDto {
        if (userRepository.existsByEmail(userRegisterFormDto.email)) {
            throw RuntimeException("이미 가입되어 있는 유저입니다.")
        }

        val userEntity = UserEntity(
            email = userRegisterFormDto.email,
            name = userRegisterFormDto.name,
            password = passwordEncoder.encode(userRegisterFormDto.password)
        )

        return userMapper.toUserInfoDto(userRepository.save(userEntity))
    }


    fun login(email: String, password: String): String {
        val userEntity = userRepository.findByEmail(email) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        if (!passwordEncoder.matches(password, userEntity.password)) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }
        return jwtTokenProvider.createToken(userEntity)
    }

    @Transactional
    fun deleteUser(username: String): String? {
        val userEntity = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        userRepository.deleteById(userEntity.id)
        return null
    }

    fun updateUser(userDTO: UserRegisterFormDto, username: String): UserInfoDto {
        val userEntity = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        userEntity.name = userDTO.name
        userEntity.password = passwordEncoder.encode(userDTO.password)
        return userMapper.toUserInfoDto(userRepository.save(userEntity))
    }


    fun searchByEmail(email: String): UserInfoDto {
        return userMapper.toUserInfoDto(userRepository.findByEmail(email) ?: throw RuntimeException("가입되지 않은 유저입니다."))
    }

}
