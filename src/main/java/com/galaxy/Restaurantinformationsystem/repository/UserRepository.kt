package com.galaxy.Restaurantinformationsystem.repository

import com.galaxy.Restaurantinformationsystem.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    /**
     * 이메일로 사용자를 찾습니다.
     *
     * @param email 사용자 이메일
     * @return 사용자 엔티티
     */
    fun findByEmail(email: String): UserEntity?

    /**
     * 이메일로 사용자가 존재하는지 확인합니다.
     *
     * @param email 사용자 이메일
     * @return 사용자가 존재하면 true, 존재하지 않으면 false
     */
    fun existsByEmail(email: String): Boolean
}
