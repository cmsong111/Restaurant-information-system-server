package com.galaxy.Restaurantinformationsystem.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 리뷰 응답 정보
 */
@Schema(description = "리뷰 응답 정보")
data class ReviewResponseDto(
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
    @field:Schema(description = "가게 ID", example = "1", required = true)
    val storeId: Long,
    @field:Schema(description = "사용자 ID", example = "user@example.com", required = true)
    val userName: String,
) {
    override fun toString(): String {
        return "ReviewResponseDto(id=$id, title='$title', content='$content', image=$image, score=$score, storeId=$storeId, userName='$userName')"
    }
}
