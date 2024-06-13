package com.galaxy.Restaurantinformationsystem.dto

import com.galaxy.Restaurantinformationsystem.common.FoodType

data class StoreResponseDto(
    val id: Long,
    val name: String,
    val callNumber: String,
    val image: String,
    val location: String,
    val category: FoodType,
    val businessHour: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val adminId: Long
) {
}
