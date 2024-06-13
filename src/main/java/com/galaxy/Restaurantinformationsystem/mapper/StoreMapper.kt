package com.galaxy.Restaurantinformationsystem.mapper

import com.galaxy.Restaurantinformationsystem.dto.*
import com.galaxy.Restaurantinformationsystem.entity.MenuEntity
import com.galaxy.Restaurantinformationsystem.entity.ReviewEntity
import com.galaxy.Restaurantinformationsystem.entity.StoreEntity
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
    fun toStoreHeaderDto(store: StoreEntity): StoreHeaderDto

    /**
     * 가게 등록 요청 DTO를 가게 엔티티로 변환합니다.
     */
    fun toStoreEntity(storeRequestDto: StoreRequestDto): StoreEntity

    /**
     * 가게 엔티티를 가게 응답 DTO로 변환합니다.
     */
    @Mapping(target = "adminId", source = "admin.id")
    fun toStoreResponseDto(store: StoreEntity): StoreResponseDto

    /**
     * ReviewEntity -> ReviewResponseDto 변환
     *
     * @param review ReviewEntity 객체
     */
    @Mapping(target = "storeId", source = "store.id")
    @Mapping(target = "userName", source = "author.email")
    fun toResponseDto(review: ReviewEntity): ReviewResponseDto

    /**
     * ReviewRequestDto -> ReviewEntity 변환
     *
     * @param dto ReviewRequestDto 객체
     */
    fun toEntity(dto: ReviewRequestDto): ReviewEntity

    /**
     * MenuRequsetDto -> MenuEntity 변환
     * @param dto MenuRequestDto 객체
     */
    fun toEntity(dto: MenuRequestDto): MenuEntity

    /**
     * MenuEntity -> MenuResponseDto 변환
     */
    @Mapping(target = "storeId", source = "store.id")
    fun toResponseDto(menu: MenuEntity): MenuResponseDto

}
