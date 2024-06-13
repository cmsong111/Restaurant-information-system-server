package com.galaxy.Restaurantinformationsystem.repository

import com.galaxy.Restaurantinformationsystem.entity.ReviewEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * 음식점 후기 Repository 클래스
 *
 * @author 김남주
 */
@Repository
interface ReviewRepository : JpaRepository<ReviewEntity, Long> {
    fun findByAuthorId(authorId: Long): List<ReviewEntity>
    fun findByStoreId(storeId: Long): List<ReviewEntity>
}
