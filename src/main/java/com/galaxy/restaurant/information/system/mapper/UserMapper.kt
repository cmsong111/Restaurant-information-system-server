package com.galaxy.restaurant.information.system.mapper

import com.galaxy.restaurant.information.system.dto.ReviewResponseDto
import com.galaxy.restaurant.information.system.dto.StoreResponseDto
import com.galaxy.restaurant.information.system.dto.UserInfoDto
import com.galaxy.restaurant.information.system.dto.UserRegisterFormDto
import com.galaxy.restaurant.information.system.entity.ReviewEntity
import com.galaxy.restaurant.information.system.entity.StoreEntity
import com.galaxy.restaurant.information.system.entity.UserEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface UserMapper {

    /**
     * 사용자 엔티티를 사용자 정보 DTO로 변환합니다.
     */
    @Mapping(
        target = "stores",
        expression = "java(user.getStores().stream().map(store -> toStoreResponseDto(store)).collect(java.util.stream.Collectors.toList()))"
    )
    @Mapping(
        target = "review",
        expression = "java(user.getReview().stream().map(reviewEntity -> toReviewResponseDto(reviewEntity)).collect(java.util.stream.Collectors.toList()))"
    )
    fun toUserInfoDto(user: UserEntity): UserInfoDto


    /**
     * 사용자 정보 DTO를 사용자 엔티티로 변환합니다.
     */
    fun toUserEntity(userRegisterFormDto: UserRegisterFormDto): UserEntity


    fun toStoreResponseDto(store: StoreEntity): StoreResponseDto
    fun toReviewResponseDto(review: ReviewEntity): ReviewResponseDto
}
