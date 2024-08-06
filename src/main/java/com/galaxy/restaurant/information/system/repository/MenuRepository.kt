package com.galaxy.restaurant.information.system.repository

import com.galaxy.restaurant.information.system.entity.MenuEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<MenuEntity, Long> {

    /**
     * 가게 id로 메뉴 찾기
     *
     * @param storeId 가게 id
     * @return 메뉴 리스트
     */
    fun findByStoreId(storeId: Long): List<MenuEntity>
}
