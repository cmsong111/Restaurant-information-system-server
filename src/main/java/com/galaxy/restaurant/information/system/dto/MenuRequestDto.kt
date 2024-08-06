package com.galaxy.restaurant.information.system.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "MenuRequestDto", description = "메뉴 등록 요청 DTO")
data class MenuRequestDto(
    @field:Schema(name = "name", description = "메뉴 이름", example = "치킨")
    val name: String,
    @field:Schema(name = "price", description = "가격", example = "10000")
    val price: Int,
    @field:Schema(name = "image", description = "이미지", example = "https://picsum.photos/1600/900")
    val image: String
) {
    override fun toString(): String {
        return "MenuRequestDto(name='$name', price=$price, image='$image')"
    }
}
