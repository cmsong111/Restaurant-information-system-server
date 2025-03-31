package com.galaxy.restaurant.information.system.restaurant.service

import com.galaxy.restaurant.information.system.restaurant.controller.request.MenuCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.request.MenuUpdateForm
import com.galaxy.restaurant.information.system.restaurant.controller.response.MenuResponse
import com.galaxy.restaurant.information.system.restaurant.entity.Menu
import com.galaxy.restaurant.information.system.restaurant.repository.MenuRepository
import com.galaxy.restaurant.information.system.restaurant.repository.RestaurantRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * 가게의 메뉴 서비스
 * @property restaurantRepository 가게 Repository
 * @property menuRepository 메뉴 Repository
 * @property storeMapper 가게 매퍼
 */
@Service
class MenuService(
    private val restaurantRepository: RestaurantRepository,
    private val menuRepository: MenuRepository,
) {
    /**
     * 가게의 메뉴를 등록합니다.
     * @param restaurantId 가게 id
     * @param menuCreateForm 메뉴 등록 요청 DTO
     * @return 등록된 메뉴
     */
    @Transactional
    fun createMenu(
        restaurantId: Long,
        menuCreateForm: MenuCreateForm,
    ): MenuResponse {
        return MenuResponse.from(
            menu = menuRepository.save(
                Menu.create(
                    name = menuCreateForm.name,
                    price = menuCreateForm.price,
                    image = "https://picsum.photos/id/500/250", // TODO : 이미지 업로드 기능 추가
                    store = restaurantRepository.findByIdOrNull(restaurantId)!!
                )
            )
        )
    }

    /**
     * 가게의 메뉴를 수정합니다.
     * @param restaurantId 가게 id
     * @param menuId 메뉴 id
     * @param menuUpdateForm 메뉴 수정 요청 DTO
     * @return 수정된 메뉴
     */
    @Transactional
    fun updateMenu(
        restaurantId: Long,
        menuId: Long,
        menuUpdateForm: MenuUpdateForm,
    ): MenuResponse {
        val menu = menuRepository.findByIdOrNull(menuId)!!

        menu.update(
            name = menuUpdateForm.name,
            price = menuUpdateForm.price,
            // image = TODO : 이미지 업로드 기능 추가
        )

        return MenuResponse.from(
            menu = menu
        )
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
