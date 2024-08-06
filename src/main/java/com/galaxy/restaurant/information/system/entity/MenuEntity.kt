package com.galaxy.restaurant.information.system.entity

import jakarta.persistence.*

@Entity
class MenuEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val name: String,
    val price: Int,
    val image: String,
    @ManyToOne
    var store: StoreEntity
) {
    override fun toString(): String {
        return "MenuEntity(id=$id, name='$name', price=$price, image='$image', store=$store)"
    }
}
