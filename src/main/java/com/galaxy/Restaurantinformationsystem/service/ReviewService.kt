package com.galaxy.Restaurantinformationsystem.service

import com.galaxy.Restaurantinformationsystem.dto.ReviewRequestDto
import com.galaxy.Restaurantinformationsystem.dto.ReviewResponseDto
import com.galaxy.Restaurantinformationsystem.entity.ReviewEntity
import com.galaxy.Restaurantinformationsystem.mapper.StoreMapper
import com.galaxy.Restaurantinformationsystem.repository.MenuRepository
import com.galaxy.Restaurantinformationsystem.repository.ReviewRepository
import com.galaxy.Restaurantinformationsystem.repository.StoreRepository
import com.galaxy.Restaurantinformationsystem.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewService(
    var reviewRepository: ReviewRepository,
    var menuRepository: MenuRepository,
    var storeRepository: StoreRepository,
    var userRepository: UserRepository,
    var storeMapper: StoreMapper
) {
    /**
     * 리뷰를 생성합니다.
     * @param storeId 가게 id
     * @param reviewFormDTO 리뷰 생성 요청 DTO
     * @param username 사용자 이름
     * @return 생성된 리뷰 정보
     */
    fun createReview(storeId: Long, reviewFormDTO: ReviewRequestDto, username: String): ReviewResponseDto {
        val reviewEntity = ReviewEntity(
            title = reviewFormDTO.title,
            content = reviewFormDTO.content,
            image = reviewFormDTO.image,
            score = reviewFormDTO.score,
            store = storeRepository.findById(storeId).get(),
            author = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        )
        return storeMapper.toResponseDto(reviewRepository.save(reviewEntity))
    }

    /**
     * 리뷰를 수정합니다.
     * @param reviewid 리뷰 id
     * @param reviewFormDTO 리뷰 수정 요청 DTO
     * @param username 사용자 이름
     * @return 수정된 리뷰 정보
     */
    fun updateReview(reviewid: Long, reviewFormDTO: ReviewRequestDto, username: String): ReviewResponseDto {
        val reviewEntity = reviewRepository.findById(reviewid).orElseThrow()
        reviewEntity.title = reviewFormDTO.title
        reviewEntity.content = reviewFormDTO.content
        reviewEntity.image = reviewFormDTO.image
        reviewEntity.score = reviewFormDTO.score
        return storeMapper.toResponseDto(reviewRepository.save(reviewEntity))
    }

    /**
     * 내 리뷰를 조회합니다.
     * @param username 사용자 이름
     * @return 내 리뷰 목록
     */
    fun getMyReview(username: String): List<ReviewResponseDto> {
        val userEntity = userRepository.findByEmail(username)  ?: throw RuntimeException("가입되지 않은 유저입니다.")
        return userEntity.review.map { reviewEntity ->
            storeMapper.toResponseDto(reviewEntity)
        }.toList()
    }

    /**
     * 리뷰를 삭제합니다.
     * @param reviewId 리뷰 id
     */
    @Transactional
    fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
    }
}
