package com.galaxy.restaurant.information.system.restaurant.controller.checker

import com.galaxy.restaurant.information.system.restaurant.repository.ReviewRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
class ReviewRoleChecker(
    private val reviewRepository: ReviewRepository
) {
    @Transactional(readOnly = true)
    fun isAuthor(
        reviewId: Long,
        email: String,
    ): Boolean {
        return reviewRepository.findByIdOrNull(reviewId)!!.author.email == email
    }
}

