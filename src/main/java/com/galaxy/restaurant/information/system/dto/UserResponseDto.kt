package com.galaxy.restaurant.information.system.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "UserResponseDto", description = "사용자 응답 정보")
data class UserResponseDto(
    @field:Schema(name = "ID", description = "ID", example = "1", required = true)
    val id: Long,
    @field:Schema(name = "이메일", description = "이메일", example = "manager@example.com", required = true)
    val email: String,
    @field:Schema(name = "이름", description = "이름", example = "김남주", required = true)
    val name: String,
    @field:Schema(name = "역할", description = "역할", example = "ROLE_MANAGER", required = true)
    val role: MutableList<String>,
    @field:Schema(name = "내가 운영중인 가게", description = "가게", required = true)
    val stores: MutableList<StoreHeaderDto>,
    @field:Schema(name = "내가 작성한 리뷰", description = "리뷰", required = true)
    val review: MutableList<ReviewResponseDto>
) {
}
