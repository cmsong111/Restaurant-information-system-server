package com.galaxy.restaurant.information.system.restaurant.service

import com.galaxy.restaurant.information.system.restaurant.controller.request.ReviewCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.response.ReviewResponse
import com.galaxy.restaurant.information.system.restaurant.entity.Review
import com.galaxy.restaurant.information.system.restaurant.repository.RestaurantRepository
import com.galaxy.restaurant.information.system.restaurant.repository.ReviewRepository
import com.galaxy.restaurant.information.system.user.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewService(
    private var reviewRepository: ReviewRepository,
    private var restaurantRepository: RestaurantRepository,
    private var userRepository: UserRepository,
) {
    /**
     * 리뷰를 생성합니다.
     * @param storeId 가게 id
     * @param reviewFormDTO 리뷰 생성 요청 DTO
     * @param username 사용자 이름
     * @return 생성된 리뷰 정보
     */
    @Transactional
    fun createReview(storeId: Long, reviewFormDTO: ReviewCreateForm, username: String): ReviewResponse {
        val review = Review(
            title = reviewFormDTO.title,
            content = reviewFormDTO.content,
            image = reviewFormDTO.image,
            score = reviewFormDTO.score,
            store = restaurantRepository.findById(storeId).get(),
            author = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        )
        return storeMapper.toResponseDto(reviewRepository.save(review))
    }

    /**
     * 리뷰를 수정합니다.
     * @param reviewid 리뷰 id
     * @param reviewFormDTO 리뷰 수정 요청 DTO
     * @param username 사용자 이름
     * @return 수정된 리뷰 정보
     */
    @Transactional
    fun updateReview(reviewid: Long, reviewFormDTO: ReviewCreateForm, username: String): ReviewResponse {
        val reviewEntity = reviewRepository.findById(reviewid).orElseThrow()
        reviewEntity.title = reviewFormDTO.title
        reviewEntity.content = reviewFormDTO.content
        reviewEntity.image = reviewFormDTO.image
        reviewEntity.score = reviewFormDTO.score
        return storeMapper.toResponseDto(reviewRepository.save(reviewEntity))
    }

    /**
     * 리뷰를 삭제합니다.
     * @param reviewId 리뷰 id
     */
    @Transactional
    fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
    }

    @Transactional(readOnly = true)
    fun getUserReviews(userId: Long, pageable: Pageable): Page<ReviewResponse> {
        return reviewRepository.findByAuthorId(userId, pageable).map { review ->
            ReviewResponse.from(review)
        }
    }
}
