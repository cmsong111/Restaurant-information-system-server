package com.galaxy.Restaurantinformationsystem.controller

import com.galaxy.Restaurantinformationsystem.dto.UserInfoDto
import com.galaxy.Restaurantinformationsystem.dto.UserRegisterFormDto
import com.galaxy.Restaurantinformationsystem.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/user")
@Tag(name = "유저 API", description = "유저 정보를 조회하는 API")
class UserController(
    val userService: UserService
) {
    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입")
    fun registerUser(@RequestBody userDTO: UserRegisterFormDto): UserInfoDto {
        return userService.registerUser(userDTO)
    }

    // 회원 정보 읽기
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인")
    fun userLogin(
        @Parameter(description = "이메일") @RequestParam(value = "email", required = true) email: String,
        @Parameter(description = "비밀번호") @RequestParam(value = "password", required = true) password: String
    ): ResponseEntity<String> {
        val token = userService.login(email, password)
        return ResponseEntity.ok(token)
    }


    @PatchMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정")
    fun userUpdate(@RequestBody userDTO: UserRegisterFormDto, principal: Principal): ResponseEntity<UserInfoDto> {
        return ResponseEntity.ok(userService.updateUser(userDTO, principal.name))
    }


    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    fun userDelete(principal: Principal): String {
        userService.deleteUser(principal.name)
        return "Delete Done"
    }

    @GetMapping
    @Operation(summary = "회원 정보 읽기", description = "회원 정보 읽기")
    @PreAuthorize("isAuthenticated()")
    fun whoAmi(principal: Principal): ResponseEntity<UserInfoDto> {
        val userDTO = userService.searchByEmail(principal.name)
        return ResponseEntity.ok(userDTO)
    }
}
