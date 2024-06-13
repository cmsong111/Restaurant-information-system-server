package com.galaxy.Restaurantinformationsystem.service

import com.galaxy.Restaurantinformationsystem.config.JwtTokenProvider
import com.galaxy.Restaurantinformationsystem.dto.UserInfoDto
import com.galaxy.Restaurantinformationsystem.dto.UserRegisterFormDto
import com.galaxy.Restaurantinformationsystem.mapper.UserMapper
import com.galaxy.Restaurantinformationsystem.repository.ReviewRepository
import com.galaxy.Restaurantinformationsystem.repository.StoreRepository
import com.galaxy.Restaurantinformationsystem.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val storeRepository: StoreRepository,
    val reviewRepository: ReviewRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtTokenProvider: JwtTokenProvider,
    val userMapper: UserMapper
) {
    fun registerUser(userRegisterFormDto: UserRegisterFormDto): UserInfoDto {
        if (userRepository.existsByEmail(userRegisterFormDto.email)) {
            throw RuntimeException("이미 가입되어 있는 유저입니다.")
        }
        userRegisterFormDto.password = passwordEncoder.encode(userRegisterFormDto.password)
        val userEntity = userMapper.toUserEntity(userRegisterFormDto)

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
