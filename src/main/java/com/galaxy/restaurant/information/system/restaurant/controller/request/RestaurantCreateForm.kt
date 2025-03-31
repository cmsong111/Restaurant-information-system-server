package com.galaxy.restaurant.information.system.restaurant.controller.request

import com.galaxy.restaurant.information.system.restaurant.entity.RestaurantType
import io.swagger.v3.oas.annotations.media.Schema


/**
 * 가게 등록 요청 DTO
 * @property name 가게 이름
 * @property callNumber 전화번호
 * @property image 이미지
 * @property location 위치
 * @property category 음식 종류
 * @property businessHour 영업시간
 * @property latitude 위도
 * @property longitude 경도
 * @author 김남주
 */
@Schema(name = "StoreRequestDto", description = "가게 등록 요청 DTO")
data class RestaurantCreateForm(
    @field:Schema(name = "name", description = "가게 이름", example = "맛집")
    val name: String,
    @field:Schema(name = "callNumber", description = "전화번호", example = "010-1234-5678")
    val callNumber: String,
    @field:Schema(name = "image", description = "이미지", example = "https://picsum.photos/1600/900")
    val image: String,
    @field:Schema(name = "location", description = "위치", example = "서울시 강남구")
    val location: String,
    @field:Schema(name = "category", description = "음식 종류", example = "KOREAN")
    val type: RestaurantType,
    @field:Schema(name = "businessHour", description = "영업시간", example = "10:00 ~ 20:00")
    val businessHour: String,
    @field:Schema(name = "description", description = "가게 설명", example = "맛있는 음식점")
    val description: String,
    @field:Schema(name = "latitude", description = "위도", example = "37.123456")
    val latitude: Double,
    @field:Schema(name = "longitude", description = "경도", example = "127.123456")
    val longitude: Double
)
