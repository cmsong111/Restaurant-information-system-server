package com.galaxy.restaurant.information.system.restaurant.entity

import com.galaxy.restaurant.information.system.user.entity.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var title: String,
    var content: String,
    var image: String?,
    var score: Int,
    @ManyToOne
    val restaurant: Restaurant,
    @ManyToOne
    val author: User
)
