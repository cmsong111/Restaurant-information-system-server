package com.galaxy.restaurant.information.system.restaurant.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Menu(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var price: Int,
    var image: String,
    @ManyToOne(targetEntity = Restaurant::class, fetch = FetchType.LAZY)
    var restaurant: Restaurant
) {
    companion object {
        fun create(
            name: String,
            price: Int,
            image: String,
            store: Restaurant
        ): Menu {
            return Menu(
                name = name,
                price = price,
                image = image,
                restaurant = store
            )
        }
    }

    fun update(
        name: String? = null,
        price: Int? = null,
        image: String? = null,
    ) {
        name?.let { this.name = it }
        price?.let { this.price = it }
        image?.let { this.image = it }
    }
}
