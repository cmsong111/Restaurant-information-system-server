package com.galaxy.restaurant.information.system.restaurant.controller.response

import com.galaxy.restaurant.information.system.restaurant.entity.Menu
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "메뉴 조회 응답 DTO")
data class MenuResponse(
    @field:Schema(description = "메뉴 id", example = "1")
    val id: Long,
    @field:Schema(description = "메뉴 이름", example = "치킨")
    val name: String,
    @field:Schema(description = "가격", example = "10000")
    val price: Int,
    @field:Schema(description = "이미지", example = "https://picsum.photos/1600/900")
    val image: String,
) {
    companion object {
        fun from(menu: Menu): MenuResponse {
            return MenuResponse(
                id = menu.id,
                name = menu.name,
                price = menu.price,
                image = menu.image
            )
        }
    }
}
