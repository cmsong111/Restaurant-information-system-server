package com.galaxy.restaurant.information.system.restaurant.controller.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

@Schema(description = "메뉴 생성 요청 DTO")
data class MenuCreateForm(
    @field:Schema(description = "메뉴 이름", example = "치킨")
    val name: String,
    @field:Schema(description = "가격", example = "10000")
    val price: Int,
    @field:Schema(description = "이미지")
    val image: MultipartFile? = null,
)
