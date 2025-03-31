package com.galaxy.restaurant.information.system.auth.controller.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email

@Schema(description = "로그인 폼 DTO")
data class LoginForm(
    @field:Schema(description = "이메일", example = "user@example.com")
    @field:Email(message = "이메일 형식이 아닙니다.")
    val email: String,
    @field:Schema(description = "비밀번호", example = "password")
    val password: String
)
