package com.galaxy.Restaurantinformationsystem.controller;

import com.galaxy.Restaurantinformationsystem.config.JwtTokenProvider;
import com.galaxy.Restaurantinformationsystem.dto.ReviewDTO;
import com.galaxy.Restaurantinformationsystem.dto.ReviewFormDTO;
import com.galaxy.Restaurantinformationsystem.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/{store_id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "리뷰 생성 API", description = "리뷰를 생성하는 API")
    public ResponseEntity<ReviewDTO> createReview(
            @Parameter(description = "가게 id") @PathVariable("store_id") Long storeId,
            @RequestBody ReviewFormDTO reviewFormDTO,
            @RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(reviewService.createReview(storeId, reviewFormDTO, token));
    }

    @PatchMapping("/{review_id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "리뷰 수정 API", description = "리뷰를 수정하는 API")
    public ResponseEntity<ReviewDTO> updateReview(
            @Parameter(description = "리뷰 ID") @PathVariable("review_id") Long storeId,
            @RequestBody ReviewFormDTO reviewFormDTO,
            @RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(reviewService.updateReview(storeId, reviewFormDTO, token));
    }

    @DeleteMapping("/{review_id}")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "리뷰 삭제 API", description = "리뷰를 삭제하는 API")
    public ResponseEntity<String> deleteReview(
            @PathVariable("review_id") Long reviewId,
            @RequestHeader(value = "Authorization") String token) {
        reviewService.deleteReview(reviewId, token);
        return ResponseEntity.ok("delete Requested");
    }

    @PostMapping("/myReview")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ReviewDTO>> readReview(
            HttpServletRequest req) {
        return ResponseEntity.ok(reviewService.getMyReview(jwtTokenProvider.getUid(req)));
    }
}
