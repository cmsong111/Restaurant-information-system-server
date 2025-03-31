package com.galaxy.restaurant.information.system.auth.controller.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 등록 폼 DTO")
data class UserRegisterForm(
    @field:Schema(description = "이메일", example = "eamil@exmaple.com")
    val email: String,
    @field:Schema(description = "비밀번호", example = "password")
    val password: String,
    @field:Schema(description = "이름", example = "name")
    val name: String
)
