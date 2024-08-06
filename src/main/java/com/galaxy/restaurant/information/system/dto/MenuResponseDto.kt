package com.galaxy.restaurant.information.system.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "MenuResponseDto", description = "메뉴 조회 응답 DTO")
data class MenuResponseDto(
    @field:Schema(name = "id", description = "메뉴 id", example = "1")
    val id: Long,
    @field:Schema(name = "name", description = "메뉴 이름", example = "치킨")
    val name: String,
    @field:Schema(name = "price", description = "가격", example = "10000")
    val price: Int,
    @field:Schema(name = "image", description = "이미지", example = "https://picsum.photos/1600/900")
    val image: String,
    @field:Schema(name = "store Id", description = "가게 id", example = "1")
    val storeId: Long
) {
    override fun toString(): String {
        return "MenuResponseDto(id=$id, name='$name', price=$price, image='$image', storeId=$storeId)"
    }
}
