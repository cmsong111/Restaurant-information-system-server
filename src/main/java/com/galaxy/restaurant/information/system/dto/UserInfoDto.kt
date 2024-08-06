package com.galaxy.restaurant.information.system.dto

import com.galaxy.restaurant.information.system.common.UserRole
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserInfoDto", description = "유저 정보")
data class UserInfoDto(
    @field:Schema(name = "id", description = "유저 ID", example = "1")
    val id: Long,
    @field:Schema(name = "name", description = "유저 이름", example = "김남주")
    val name: String,
    @field:Schema(name = "email", description = "유저 이메일", example = "example@example.com")
    val email: String,
    @field:Schema(name = "role", description = "유저 권한", example = "ROLE_USER")
    val role: MutableList<UserRole>,
    @field:Schema(name = "stores", description = "내가 등록한 가게")
    val stores: MutableList<StoreResponseDto>,
    @field:Schema(name = "review", description = "내가 작성한 리뷰")
    val review: MutableList<ReviewResponseDto>
) {
    override fun toString(): String {
        return "UserInfoDto(id=$id, name='$name', email='$email', roles=$role, stores=$stores, review=$review)"
    }
}
