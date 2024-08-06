package com.galaxy.restaurant.information.system.service

import com.galaxy.restaurant.information.system.dto.MenuRequestDto
import com.galaxy.restaurant.information.system.dto.MenuResponseDto
import com.galaxy.restaurant.information.system.entity.MenuEntity
import com.galaxy.restaurant.information.system.mapper.StoreMapper
import com.galaxy.restaurant.information.system.repository.MenuRepository
import com.galaxy.restaurant.information.system.repository.StoreRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

/**
 * 가게의 메뉴 서비스
 * @property storeRepository 가게 Repository
 * @property menuRepository 메뉴 Repository
 * @property storeMapper 가게 매퍼
 */
@Service
class MenuService(
    private val storeRepository: StoreRepository,
    private val menuRepository: MenuRepository,
    private val storeMapper: StoreMapper,
) {
    /**
     * 가게의 메뉴 목록을 조회합니다.
     * @param storeId 가게 id
     * @return 가게의 메뉴 목록
     */
    fun getMenuDTO(storeId: Long): List<MenuResponseDto> {
        val menus: List<MenuEntity> = menuRepository.findByStoreId(storeId)
        return menus.map { menuEntity ->
            storeMapper.toResponseDto(menuEntity)
        }.toList()
    }

    /**
     * 가게의 메뉴를 등록합니다.
     * @param storeId 가게 id
     * @param menuRequestDto 메뉴 등록 요청 DTO
     */
    fun createMenuDTO(storeId: Long, menuRequestDto: MenuRequestDto): MenuResponseDto {
        val menu = storeMapper.toEntity(menuRequestDto)
        menu.store = storeRepository.findById(storeId).get()
        return storeMapper.toResponseDto(menuRepository.save(menu))
    }

    /**
     * 가게의 메뉴를 수정합니다.
     * @param storeId 가게 id
     * @param menuRequestDto 메뉴 수정 요청 DTO
     * @param menuId 메뉴 id
     * @return 수정된 메뉴
     */
    fun updateMenuDTO(storeId: Long, menuRequestDto: MenuRequestDto, menuId: Long): MenuResponseDto {
        val menu = storeMapper.toEntity(menuRequestDto)
        menu.store = storeRepository.findById(storeId).get()
        menu.id = menuId
        return storeMapper.toResponseDto(menuRepository.save(menu))
    }

    /**
     * 가게의 메뉴를 삭제합니다.
     * @param menuId 메뉴 id
     */
    @Transactional
    fun deleteMenu(menuId: Long) {
        menuRepository.deleteById(menuId)
    }
}
