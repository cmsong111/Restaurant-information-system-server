package com.galaxy.restaurant.information.system.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserRegisterFormDto", description = "유저 등록 폼 DTO")
data class UserRegisterFormDto(
    @field:Schema(name = "email", description = "이메일", example = "eamil@exmaple.com")
    var email: String,
    @field:Schema(name = "password", description = "비밀번호", example = "password")
    var password: String,
    @field:Schema(name = "name", description = "이름", example = "name")
    var name: String
) {
    override fun toString(): String {
        return "UserRegisterFormDto(email='$email', password='$password', name='$name')"
    }
}
