package com.galaxy.Restaurantinformationsystem.config

import com.galaxy.Restaurantinformationsystem.entity.ReviewEntity
import com.galaxy.Restaurantinformationsystem.entity.StoreEntity
import com.galaxy.Restaurantinformationsystem.repository.ReviewRepository
import com.galaxy.Restaurantinformationsystem.repository.StoreRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class Checker(
    val storeRepository: StoreRepository,
    val reviewRepository: ReviewRepository
) {

    /**
     * 해당 사용자가 해당 가게의 관리자인지 확인
     * @param storeId 가게 id
     * @return 해당 사용자가 해당 가게의 관리자인지 여부
     */
    fun isStoreOwner(storeId: Long): Boolean {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication as Authentication
        val store: StoreEntity = storeRepository.findById(storeId).orElseThrow { IllegalArgumentException("가게를 찾을 수 없습니다.") }
        return store.admin.email == authentication.name
    }

    /**
     * 해당 사용자가 해당 리뷰의 작성자인지 확인
     *
     * @param reviewId 리뷰 id
     * @return 해당 사용자가 해당 리뷰의 작성자인지 여부
     */
    fun isReviewAuthor(reviewId: Long): Boolean {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication as Authentication
        val review : ReviewEntity = reviewRepository.findById(reviewId).orElseThrow { IllegalArgumentException("리뷰를 찾을 수 없습니다.") }
        return review.author.email == authentication.name
    }
}
