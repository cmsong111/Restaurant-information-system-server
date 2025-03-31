package com.galaxy.restaurant.information.system.auth.controller

import com.galaxy.restaurant.information.system.auth.controller.request.LoginForm
import com.galaxy.restaurant.information.system.auth.controller.request.UserRegisterForm
import com.galaxy.restaurant.information.system.auth.controller.response.TokenResponse
import com.galaxy.restaurant.information.system.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입")
    fun registerUser(
        @RequestBody userDTO: UserRegisterForm,
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(
            TokenResponse(
                token = authService.registerUser(userDTO)
            )
        )
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인")
    fun userLogin(
        @RequestBody @Valid loginForm: LoginForm,
    ): ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(
            TokenResponse(
                token = authService.login(
                    email = loginForm.email,
                    password = loginForm.password,
                )
            )
        )
    }

    @PostMapping("/check-email")
    @Operation(summary = "이메일 중복 체크", description = "이메일 중복 체크")
    fun checkEmail(
        @RequestParam email: String,
    ): ResponseEntity<Boolean> {
        return ResponseEntity.ok(
            authService.isAvailableEmail(
                email = email,
            )
        )
    }
}
