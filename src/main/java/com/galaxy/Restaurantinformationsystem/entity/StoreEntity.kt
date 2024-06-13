package com.galaxy.Restaurantinformationsystem.entity

import com.galaxy.Restaurantinformationsystem.common.FoodType
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp

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
 * @property updatedAt 가게 수정일
 * @property latitude 위도
 * @property longitude 경도
 * @property admin 가게 등록자
 * @property reviews 가게 리뷰
 * @property menus 가게 메뉴
 */
@Entity
@EntityListeners(AuditingEntityListener::class)
class StoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String,
    var callNumber: String,
    var image: String,
    var location: String,
    @Enumerated(EnumType.STRING)
    var category: FoodType,
    var businessHour: String,
    @Column(columnDefinition = "LONGTEXT")
    var description: String,
    @CreatedDate
    val createdAt: Timestamp = Timestamp(System.currentTimeMillis()),
    @LastModifiedDate
    var updatedAt: Timestamp = Timestamp(System.currentTimeMillis()),
    var latitude: Double,
    var longitude: Double,
    @ManyToOne
    var admin: UserEntity,
    @OneToMany(cascade = [CascadeType.REMOVE])
    val reviews: MutableList<ReviewEntity> = mutableListOf(),
    @OneToMany(cascade = [CascadeType.REMOVE])
    val menus: MutableList<MenuEntity> = mutableListOf()
) {
    override fun toString(): String {
        return "StoreEntity(id=$id, name='$name', callNumber='$callNumber', image='$image', location='$location', category=$category, businessHour='$businessHour', description='$description', createdAt=$createdAt, updatedAt=$updatedAt, latitude=$latitude, longitude=$longitude, admin=$admin, reviews=$reviews, menus=$menus)"
    }
}
