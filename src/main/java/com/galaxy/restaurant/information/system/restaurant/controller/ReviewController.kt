package com.galaxy.restaurant.information.system.restaurant.controller

import com.galaxy.restaurant.information.system.restaurant.controller.request.ReviewCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.response.ReviewResponse
import com.galaxy.restaurant.information.system.restaurant.service.ReviewService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import java.net.URI
import java.security.Principal
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 리뷰 Controller 클래스
 */
@Tag(name = "리뷰 API", description = "리뷰 API를 관리하는 Controller")
@RestController
@RequestMapping("/api/v1/restaurants/{restaurantId}/reviews")
class ReviewController(
    private val reviewService: ReviewService
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "리뷰 생성 API", description = "리뷰를 생성하는 API")
    fun createReview(
        @PathVariable @Parameter(description = "가게 id") restaurantId: Long,
        @RequestBody reviewFormDTO: ReviewCreateForm,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<ReviewResponse> {
        return reviewService.createReview(
            restaurantId,
            reviewFormDTO,
            principal.name
        ).let { review ->
            ResponseEntity
                .created(URI.create("/review/${review.id}"))
                .body(review)
        }
    }

    @PatchMapping("/{reviewId}", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @PreAuthorize("@reviewRoleChecker.isAuthor(#reviewId, #principal.name)")
    @Operation(summary = "리뷰 수정 API", description = "리뷰를 수정하는 API")
    fun updateReview(
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
        @PathVariable @Parameter(description = "리뷰 ID") reviewId: Long,
        @RequestBody reviewFormDTO: ReviewCreateForm,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<ReviewResponse> {
        return ResponseEntity.ok(
            reviewService.updateReview(
                reviewId,
                reviewFormDTO,
                principal.name,
            )
        )
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("@reviewRoleChecker.isAuthor(#reviewId, #principal.name)")
    @Operation(summary = "리뷰 삭제 API", description = "리뷰를 삭제하는 API")
    fun deleteReview(
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
        @PathVariable @Parameter(description = "리뷰 ID") reviewId: Long,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<String> {
        reviewService.deleteReview(reviewId)
        return ResponseEntity.noContent().build()
    }
}
