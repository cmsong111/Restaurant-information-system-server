package com.galaxy.restaurant.information.system.restaurant.controller

import com.galaxy.restaurant.information.system.restaurant.controller.response.ReviewResponse
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreSummary
import com.galaxy.restaurant.information.system.restaurant.service.ReviewService
import com.galaxy.restaurant.information.system.restaurant.service.StoreService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users/{userId}")
@Tag(name = "유저 API", description = "유저 정보를 조회하는 API")
class UsersRestaurantController(
    private val reviewService: ReviewService,
    private val storeService: StoreService,
) {
    @GetMapping("/reviews")
    @Operation(summary = "특정 유저의 리뷰 조회", description = "특정 유저의 리뷰를 조회하는 API")
    fun getUserReviews(
        @PathVariable @Parameter(description = "유저 ID") userId: Long,
        @ParameterObject pageable: Pageable
    ): ResponseEntity<PagedModel<ReviewResponse>> {
        return ResponseEntity.ok(
            PagedModel(
                reviewService.getUserReviews(
                    userId = userId,
                    pageable = pageable
                )
            )
        )
    }

    @GetMapping("/stores")
    @Operation(summary = "특정 유저의 가게 조회")
    fun getUserStores(
        @PathVariable @Parameter(description = "유저 ID") userId: Long,
        @ParameterObject pageable: Pageable,
    ): ResponseEntity<List<StoreSummary>> {
        return ResponseEntity.ok(storeService.findMyStore(principal.name))
    }
}
