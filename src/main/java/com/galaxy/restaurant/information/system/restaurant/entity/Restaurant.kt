package com.galaxy.restaurant.information.system.restaurant.entity

import com.galaxy.restaurant.information.system.common.entity.BaseEntity
import com.galaxy.restaurant.information.system.user.entity.User
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**
 * 가게 엔티티 클래스
 *
 * @property id 가게 PK (자동 생성)
 * @property name 가게 이름
 * @property callNumber 전화번호
 * @property image 이미지
 * @property location 위치
 * @property category 음식 종류
 * @property businessHour 영업시간
 * @property description 가게 설명
 * @property createdAt 가게 생성일
 *
 * @property updatedAt 가게 수정일
 * @property latitude 위도
 * @property longitude 경도
 * @property admin 가게 등록자
 * @property reviews 가게 리뷰
 * @property menus 가게 메뉴
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class Restaurant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String,
    var tel: String? = null,
    var thumbnail: String? = null,
    var businessHour: String? = null,
    @Column(columnDefinition = "LONGTEXT")
    var description: String,

    /** 가게 유형 (족발, 보쌈 등) */
    @Enumerated(EnumType.STRING)
    var type: RestaurantType,

    /** 가게 인증 (착한가격업소 등) */
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var certificates: MutableSet<RestaurantCertification> = mutableSetOf(),

    // 위치
    var location: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,

    @ManyToOne
    val admin: User,
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    val reviews: MutableList<Review> = mutableListOf(),
    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    val menus: MutableList<Menu> = mutableListOf()
) : BaseEntity()
