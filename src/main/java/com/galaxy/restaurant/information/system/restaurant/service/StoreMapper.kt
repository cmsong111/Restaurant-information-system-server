package com.galaxy.restaurant.information.system.restaurant.service

import com.galaxy.restaurant.information.system.restaurant.controller.request.MenuRequestDto
import com.galaxy.restaurant.information.system.restaurant.controller.request.RestaurantCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.request.ReviewCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.response.MenuResponse
import com.galaxy.restaurant.information.system.restaurant.controller.response.ReviewResponse
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreResponseDto
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreSummary
import com.galaxy.restaurant.information.system.restaurant.entity.Menu
import com.galaxy.restaurant.information.system.restaurant.entity.Restaurant
import com.galaxy.restaurant.information.system.restaurant.entity.Review
import org.mapstruct.Mapper
import org.mapstruct.Mapping

/**
 * 가게 관련 매퍼
 */
@Mapper(componentModel = "spring")
interface StoreMapper {

    /**
     * 가게 엔티티를 가게 헤더 DTO로 변환합니다.
     */
    fun toStoreHeaderDto(store: Restaurant): StoreSummary

    /**
     * 가게 등록 요청 DTO를 가게 엔티티로 변환합니다.
     */
    fun toStoreEntity(storeRequestDto: RestaurantCreateForm): Restaurant

    /**
     * 가게 엔티티를 가게 응답 DTO로 변환합니다.
     */
    @Mapping(target = "adminId", source = "admin.id")
    fun toStoreResponseDto(store: Restaurant): StoreResponseDto

    /**
     * ReviewEntity -> ReviewResponseDto 변환
     *
     * @param review ReviewEntity 객체
     */
    @Mapping(target = "storeId", source = "store.id")
    @Mapping(target = "userName", source = "author.email")
    fun toResponseDto(review: Review): ReviewResponse

    /**
     * ReviewRequestDto -> ReviewEntity 변환
     *
     * @param dto ReviewRequestDto 객체
     */
    fun toEntity(dto: ReviewCreateForm): Review

    /**
     * MenuRequsetDto -> MenuEntity 변환
     * @param dto MenuRequestDto 객체
     */
    fun toEntity(dto: MenuRequestDto): Menu

    /**
     * MenuEntity -> MenuResponseDto 변환
     */
    @Mapping(target = "storeId", source = "store.id")
    fun toResponseDto(menu: Menu): MenuResponse

}
