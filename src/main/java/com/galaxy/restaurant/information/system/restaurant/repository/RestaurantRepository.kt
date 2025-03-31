package com.galaxy.restaurant.information.system.restaurant.repository

import com.galaxy.restaurant.information.system.restaurant.entity.FoodType
import com.galaxy.restaurant.information.system.restaurant.entity.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RestaurantRepository : JpaRepository<Restaurant, Long> {
    /**
     * 가게 이름, 카테고리, 위도, 경도로 가게 찾기
     *
     * @param name      가게 이름
     * @param category  카테고리
     * @param latitude  위도
     * @param longitude 경도
     * @return 가게 리스트
     */
    @Query(
        "select m from Restaurant m where (:name is null or m.name like %:name%) and  (:category is null or m.category = :category) and " +
                "(:latitude is null or m.latitude between :latitude-0.5 and :latitude+0.5) and (:longitude is null or m.longitude between :longitude-0.5 and :longitude+0.5)"
    )
    fun findStore(name: String?, category: FoodType?, latitude: Double?, longitude: Double?): List<Restaurant>

    /**
     * 관리자 이메일로 가게 찾기
     *
     * @param email 관리자 이메일
     * @return 가게 리스트
     */
    fun findByAdminEmail(email: String): List<Restaurant>

    /**
     * 근처 지역의 가게를 랜덤으로 하나 가져오기
     *
     * @param latitude  위도
     * @param longitude 경도
     * @return 가게
     */
    @Query(value = "select m from Restaurant m where (:latitude is null or m.latitude between :latitude-0.5 and :latitude+0.5) and (:longitude is null or m.longitude between :longitude-0.5 and :longitude+0.5) order by rand() limit 1")
    fun findRandomStore(latitude: Double?, longitude: Double?): Restaurant
}
