package com.galaxy.restaurant.information.system.restaurant.controller.request

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.web.multipart.MultipartFile

@Schema(description = "메뉴 등록 요청 DTO")
data class MenuUpdateForm(
    @field:Schema(description = "메뉴 이름", example = "치킨")
    val name: String? = null,
    @field:Schema(description = "가격", example = "10000")
    val price: Int? = null,
    @field:Schema(description = "이미지")
    val image: MultipartFile? = null,
)
