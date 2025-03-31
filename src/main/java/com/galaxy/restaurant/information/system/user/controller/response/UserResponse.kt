package com.galaxy.restaurant.information.system.user.controller.response

import com.galaxy.restaurant.information.system.restaurant.controller.response.ReviewResponse
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreSummary
import com.galaxy.restaurant.information.system.user.entity.User
import com.galaxy.restaurant.information.system.user.entity.UserRole
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "사용자 응답 정보")
data class UserResponse(
    @field:Schema(description = "ID", example = "1")
    val id: Long,
    @field:Schema(description = "이메일", example = "manager@example.com")
    val email: String,
    @field:Schema(description = "이름", example = "김남주")
    val name: String,
    @field:Schema(description = "프로필 이미지", example = "https://example.com/profile.jpg")
    var profileImage: String?,
    @field:Schema(description = "역할", example = "ROLE_MANAGER")
    val roles: Set<UserRole>,
    @field:Schema(description = "내가 보유한 가게")
    val stores: List<StoreSummary> = emptyList(),
    @field:Schema(description = "내가 작성한 리뷰")
    val review: List<ReviewResponse> = emptyList(),
) {
    companion object {
        fun from(
            user: User,
            stores: List<StoreSummary> = emptyList(),
            review: List<ReviewResponse> = emptyList(),
        ): UserResponse {
            return UserResponse(
                id = user.id,
                email = user.email,
                name = user.name,
                profileImage = user.profileImage,
                roles = user.roles.toSet(),
                stores = stores,
                review = review,
            )
        }
    }
}
