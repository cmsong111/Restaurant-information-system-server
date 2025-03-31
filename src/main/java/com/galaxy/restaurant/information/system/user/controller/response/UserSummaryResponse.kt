package com.galaxy.restaurant.information.system.user.controller.response

import com.galaxy.restaurant.information.system.user.entity.User
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "유저 정보")
data class UserSummaryResponse(
    @field:Schema(description = "ID", example = "1")
    val id: Long,
    @field:Schema(description = "이메일", example = "manager@example.com")
    val email: String,
    @field:Schema(description = "이름", example = "김남주")
    val name: String,
    @field:Schema(description = "프로필 이미지", example = "https://example.com/profile.jpg")
    var profileImage: String?
) {
    companion object {
        fun from(
            user: User
        ): UserSummaryResponse {
            return UserSummaryResponse(
                id = user.id,
                email = user.email,
                name = user.name,
                profileImage = user.profileImage
            )
        }
    }
}
