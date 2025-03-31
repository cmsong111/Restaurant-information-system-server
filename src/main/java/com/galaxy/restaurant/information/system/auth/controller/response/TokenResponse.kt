package com.galaxy.restaurant.information.system.auth.controller.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "JWT 토큰")
data class TokenResponse(
    @field:Schema(description = "Access Token", example = "eye...eY...")
    val token: String
)
