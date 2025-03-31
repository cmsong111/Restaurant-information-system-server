package com.galaxy.restaurant.information.system.user.controller

import com.galaxy.restaurant.information.system.auth.controller.request.UserRegisterForm
import com.galaxy.restaurant.information.system.user.controller.response.UserResponse
import com.galaxy.restaurant.information.system.user.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import java.security.Principal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("isAuthenticated()")
@Tag(name = "유저 API", description = "유저 정보를 조회하는 API")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    @Operation(summary = "회원 정보 읽기", description = "회원 정보 읽기")
    fun whoAmi(
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(
            userService.searchByEmail(principal.name)
        )
    }

    @PatchMapping
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정")
    fun userUpdate(
        @RequestBody userDTO: UserRegisterForm,
        @AuthenticationPrincipal principal: Principal,
    ): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(
            userService.updateUser(userDTO, principal.name)
        )
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    fun userDelete(
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<Unit> {
        userService.deleteUser(principal.name)
        return ResponseEntity.noContent().build()
    }
}
