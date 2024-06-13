package com.galaxy.Restaurantinformationsystem.entity

import com.galaxy.Restaurantinformationsystem.common.UserRole
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp

/**
 * 사용자 엔티티 클래스
 *
 * @property id 사용자 PK (자동 생성)
 * @property email 사용자 이메일
 * @property password 사용자 비밀번호
 * @property name 사용자 이름
 * @property role 사용자 역할
 * @property stores 사용자가 등록한 가게
 * @property review 사용자가 작성한 리뷰
 * @constructor 사용자 엔티티 생성자
 */
@Entity(name = "users")
@EntityListeners(AuditingEntityListener::class)
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true)
    var email: String,
    var password: String,
    var name: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val role: MutableList<UserRole> = mutableListOf(UserRole.USER),

    @OneToMany(cascade = [CascadeType.ALL])
    val stores: MutableList<StoreEntity>,

    @OneToMany(cascade = [CascadeType.ALL])
    val review: MutableList<ReviewEntity>,

    @CreatedDate
    val createdAt: Timestamp = Timestamp(System.currentTimeMillis()),

    @LastModifiedDate
    var updatedAt: Timestamp = Timestamp(System.currentTimeMillis()),

    ) {
    override fun toString(): String {
        return "UserEntity(id=$id, email='$email', password='$password', name='$name', role=$role, stores=$stores, review=$review)"
    }
}
