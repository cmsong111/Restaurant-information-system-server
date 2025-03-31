package com.galaxy.restaurant.information.system.restaurant.controller.checker

import com.galaxy.restaurant.information.system.restaurant.repository.RestaurantRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class RestaurantRoleChecker(
    private val restaurantRepository: RestaurantRepository,
) {
    @Transactional(readOnly = true)
    fun isAdmin(
        restaurantId: Long,
        email: String,
    ): Boolean {
        return restaurantRepository.findByIdOrNull(restaurantId)!!.admin.email == email
    }
}
