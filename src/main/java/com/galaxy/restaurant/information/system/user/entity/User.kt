package com.galaxy.restaurant.information.system.user.entity

import com.galaxy.restaurant.information.system.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

/**
 * 사용자 엔티티 클래스
 *
 * @property id 사용자 PK (자동 생성)
 * @property email 사용자 이메일
 * @property password 사용자 비밀번호
 * @property name 사용자 이름
 * @property roles 사용자 역할
 * @property stores 사용자가 등록한 가게
 * @property review 사용자가 작성한 리뷰
 * @constructor 사용자 엔티티 생성자
 */
@Entity(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(unique = true)
    val email: String,
    var password: String,
    var name: String,
    var profileImage: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val roles: MutableSet<UserRole> = mutableSetOf(UserRole.USER),
) : BaseEntity()
