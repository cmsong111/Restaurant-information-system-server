package com.galaxy.restaurant.information.system.restaurant.repository

import com.galaxy.restaurant.information.system.restaurant.entity.Review
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 음식점 후기 Repository 클래스
 *
 * @author 김남주
 */
@Repository
interface ReviewRepository : JpaRepository<Review, Long> {
    fun findByAuthorId(
        authorId: Long,
        pageable: Pageable
    ): Page<Review>
}
