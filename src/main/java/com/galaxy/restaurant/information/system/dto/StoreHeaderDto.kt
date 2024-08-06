package com.galaxy.restaurant.information.system.dto

import com.galaxy.restaurant.information.system.common.FoodType
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "StoreHeaderDto", description = "가게 헤더 정보")
data class StoreHeaderDto(
    @field:Schema(name = "id", description = "가게 ID", example = "1")
    val id: Long,
    @field:Schema(name = "name", description = "가게 이름", example = "맛집")
    val name: String,
    @field:Schema(name = "image", description = "이미지", example = "https://picsum.photos/1600/900")
    val image: String,
    @field:Schema(name = "location", description = "위치", example = "서울시 강남구")
    val location: String,
    @field:Schema(name = "category", description = "음식 종류", example = "KOREAN")
    val category: FoodType,
    @field:Schema(name = "businessHour", description = "영업시간", example = "10:00 ~ 20:00")
    val businessHour: String,
    @field:Schema(name = "description", description = "가게 설명", example = "맛있는 음식점")
    var description: String,
) {
}
