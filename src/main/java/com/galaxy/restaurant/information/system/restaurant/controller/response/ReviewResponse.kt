package com.galaxy.restaurant.information.system.restaurant.controller.response

import com.galaxy.restaurant.information.system.restaurant.entity.Review
import com.galaxy.restaurant.information.system.user.controller.response.UserSummaryResponse
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 리뷰 응답 정보
 */
@Schema(description = "리뷰 응답 정보")
data class ReviewResponse(
    @field:Schema(description = "리뷰 ID", example = "1", required = true)
    val id: Long,
    @field:Schema(description = "제목", example = "맛있어요", required = true)
    val title: String,
    @field:Schema(description = "내용", example = "맛있어요", required = true)
    val content: String,
    @field:Schema(description = "이미지", example = "https://www.naver.com", required = true)
    val image: String?,
    @field:Schema(description = "평점", example = "5", required = true)
    val score: Int,
    val user: UserSummaryResponse,
) {
    companion object {
        fun from(review: Review) =
            ReviewResponse(
                id = review.id,
                title = review.title,
                content = review.content,
                image = review.image,
                score = review.score,
                user = UserSummaryResponse.from(review.author),
            )
    }
}
