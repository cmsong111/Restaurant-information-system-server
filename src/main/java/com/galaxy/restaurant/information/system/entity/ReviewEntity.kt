package com.galaxy.restaurant.information.system.entity

import jakarta.persistence.*

@Entity
class ReviewEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var title: String,
    var content: String,
    var image: String?,
    var score: Int,
    @ManyToOne
    val store: StoreEntity,
    @ManyToOne
    val author: UserEntity
) {
    override fun toString(): String {
        return "ReviewEntity(id=$id, title='$title', content='$content', image=$image, score=$score, store=$store, author=$author)"
    }
}
