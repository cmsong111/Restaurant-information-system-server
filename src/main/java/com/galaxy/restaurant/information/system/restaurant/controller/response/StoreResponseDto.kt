package com.galaxy.restaurant.information.system.restaurant.controller.response

import com.galaxy.restaurant.information.system.restaurant.entity.RestaurantType

data class StoreResponseDto(
    val id: Long,
    val name: String,
    val callNumber: String,
    val image: String,
    val location: String,
    val type: RestaurantType,
    val businessHour: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val adminId: Long
) {
}
