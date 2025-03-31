package com.galaxy.restaurant.information.system.restaurant.controller.request

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 리뷰 요청 정보
 *
 * @property title 제목
 * @property content 내용
 * @property image 이미지
 * @property score 평점
 */
@Schema(description = "리뷰 요청 정보")
data class ReviewCreateForm(
    @field:Schema(description = "제목", example = "맛있어요", required = true)
    val title: String,
    @field:Schema(description = "내용", example = "맛있어요", required = true)
    val content: String,
    @field:Schema(description = "이미지", example = "https://www.naver.com", required = true)
    val image: String?,
    @field:Schema(description = "평점", example = "5", required = true)
    val score: Int,
) {
    override fun toString(): String {
        return "ReviewRequestDto(title='$title', content='$content', image=$image, score=$score)"
    }
}
