package com.galaxy.restaurant.information.system.restaurant.controller

import com.galaxy.restaurant.information.system.restaurant.controller.request.MenuCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.request.MenuUpdateForm
import com.galaxy.restaurant.information.system.restaurant.controller.response.MenuResponse
import com.galaxy.restaurant.information.system.restaurant.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import java.net.URI
import java.security.Principal
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "메뉴 API", description = "메뉴 정보를 조회하는 API")
@RestController
@RequestMapping("/api/v1/restaurants/{restaurantId}/menus")
class MenuController(
    private val menuService: MenuService
) {
    @PostMapping
    @PreAuthorize("@restaurantRoleChecker.isAdmin(#restaurantId,#principal.name)")
    @Operation(summary = "메뉴 생성 API", description = "메뉴를 생성하는 API")
    fun createMenu(
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
        @RequestBody menuCreateForm: MenuCreateForm,
        @AuthenticationPrincipal principal: Principal,
    ): ResponseEntity<MenuResponse> {
        return menuService.createMenu(
            restaurantId = restaurantId,
            menuCreateForm = menuCreateForm
        ).let { menu ->
            ResponseEntity
                .created(URI.create("/api/v1/restaurants/${restaurantId}/menus/${menu.id}"))
                .body(menu)
        }
    }

    @PatchMapping("/{menuId}")
    @PreAuthorize("@restaurantRoleChecker.isAdmin(#restaurantId,#principal.name)")
    @Operation(summary = "메뉴 수정 API", description = "메뉴를 수정하는 API")
    fun updateMenu(
        @RequestBody menuUpdateForm: MenuUpdateForm,
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
        @PathVariable @Parameter(description = "메뉴 ID") menuId: Long,
        @AuthenticationPrincipal principal: Principal,
    ): MenuResponse {
        return menuService.updateMenu(
            restaurantId = restaurantId,
            menuId = menuId,
            menuUpdateForm = menuUpdateForm
        )
    }

    @DeleteMapping("/{menuId}")
    @PreAuthorize("@restaurantRoleChecker.isAdmin(#restaurantId,#principal.name)")
    @Operation(summary = "메뉴 삭제 API", description = "메뉴를 삭제하는 API")
    fun deleteMenu(
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
        @PathVariable @Parameter(description = "메뉴 ID") menuId: Long,
        @AuthenticationPrincipal principal: Principal,
    ): ResponseEntity<Unit> {
        menuService.deleteMenu(menuId)
        return ResponseEntity.noContent().build()
    }
}
