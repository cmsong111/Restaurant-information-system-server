package com.galaxy.Restaurantinformationsystem.controller

import com.galaxy.Restaurantinformationsystem.dto.ReviewRequestDto
import com.galaxy.Restaurantinformationsystem.dto.ReviewResponseDto
import com.galaxy.Restaurantinformationsystem.service.ReviewService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.security.Principal

/**
 * 리뷰 Controller 클래스
 */
@RestController
class ReviewController(
    val reviewService: ReviewService
) {
    @PostMapping("/{store_id}/review")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "리뷰 생성 API", description = "리뷰를 생성하는 API")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "리뷰 생성 성공",
                content = [Content(schema = Schema(implementation = ReviewResponseDto::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "리뷰 생성 실패",
                content = [Content(schema = Schema(hidden = true))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "권한 없음",
                content = [Content(schema = Schema(hidden = true))]
            )]
    )
    fun createReview(
        @Parameter(description = "가게 id") @PathVariable("store_id") storeId: Long,
        @RequestBody reviewFormDTO: ReviewRequestDto,
        principal: Principal
    ): ResponseEntity<ReviewResponseDto> {
        val reviewResponseDto = reviewService.createReview(storeId, reviewFormDTO, principal.name)
        return ResponseEntity.created(URI.create("/review/${reviewResponseDto.id}")).body(reviewResponseDto)
    }

    @PatchMapping("/review/{review_id}")
    @PreAuthorize("@checker.isReviewAuthor(#review_id)")
    @Operation(summary = "리뷰 수정 API", description = "리뷰를 수정하는 API")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "리뷰 수정 성공",
                content = [Content(schema = Schema(implementation = ReviewResponseDto::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "리뷰 수정 실패",
                content = [Content(schema = Schema(hidden = true))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "권한 없음",
                content = [Content(schema = Schema(hidden = true))]
            )]
    )
    fun updateReview(
        @Parameter(description = "리뷰 ID") @PathVariable("review_id") reviewId: Long,
        @RequestBody reviewFormDTO: ReviewRequestDto,
        principal: Principal
    ): ResponseEntity<ReviewResponseDto> {
        return ResponseEntity.ok(reviewService.updateReview(reviewId, reviewFormDTO, principal.name))
    }

    @DeleteMapping("/review/{review_id}")
    @PreAuthorize("@checker.isReviewAuthor(#review_id)")
    @Operation(summary = "리뷰 삭제 API", description = "리뷰를 삭제하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "204", description = "리뷰 삭제 성공"),
            ApiResponse(responseCode = "403", description = "권한 없음"),
            ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없음")
        ]
    )
    fun deleteReview(
        @PathVariable("review_id") reviewId: Long,
    ): ResponseEntity<String> {
        reviewService.deleteReview(reviewId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/review/my")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "내 리뷰 조회 API", description = "내가 작성한 리뷰를 조회하는 API")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "내 리뷰 조회 성공",
                content = [Content(schema = Schema(implementation = List::class))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "권한 없음",
                content = [Content(schema = Schema(hidden = true))]
            )]
    )
    fun readReview(
        principal: Principal
    ): ResponseEntity<List<ReviewResponseDto>> {
        return ResponseEntity.ok(reviewService.getMyReview(principal.name))
    }
}
