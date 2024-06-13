package com.galaxy.Restaurantinformationsystem.controller

import com.galaxy.Restaurantinformationsystem.dto.MenuRequestDto
import com.galaxy.Restaurantinformationsystem.dto.MenuResponseDto
import com.galaxy.Restaurantinformationsystem.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URI

@Tag(name = "Menu", description = "메뉴 API")
@RestController
class MenuController(
    val menuService: MenuService
) {

    @GetMapping("/{store_id}/menu")
    @Operation(summary = "메뉴 조회 API", description = "메뉴를 조회하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메뉴 조회 성공")
        ]
    )
    fun getMenu(@PathVariable("store_id") storeId: Long): List<MenuResponseDto> {
        return menuService.getMenuDTO(storeId)
    }

    @PostMapping("/{store_id}/menu")
    @PreAuthorize("@checker.isStoreOwner(#store_id)")
    @Operation(summary = "메뉴 생성 API", description = "메뉴를 생성하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "메뉴 생성 성공"),
            ApiResponse(responseCode = "403", description = "권한 없음")
        ]
    )
    fun createMenu(
        @PathVariable("store_id") storeId: Long,
        @RequestBody menuRequestDto: MenuRequestDto
    ): ResponseEntity<MenuResponseDto> {
        val menuResponseDto = menuService.createMenuDTO(storeId, menuRequestDto)
        return ResponseEntity.created(URI.create("/menu/${menuResponseDto.id}")).body(menuResponseDto)
    }

    @PatchMapping("/{store_id}/menu/{menu_id}")
    @PreAuthorize("@checker.isStoreOwner(#store_id)")
    @Operation(summary = "메뉴 수정 API", description = "메뉴를 수정하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메뉴 수정 성공"),
            ApiResponse(responseCode = "403", description = "권한 없음"),
            ApiResponse(responseCode = "404", description = "메뉴를 찾을 수 없음")
        ]
    )
    fun updateMenu(
        @RequestBody menuRequestDto: MenuRequestDto,
        @PathVariable("store_id") storeId: Long,
        @PathVariable("menu_id") menuId: Long
    ): MenuResponseDto {
        return menuService.updateMenuDTO(storeId, menuRequestDto, menuId)
    }


    @DeleteMapping("/{store_id}/menu/{menu_id}")
    @PreAuthorize("@checker.isStoreOwner(#store_id)")
    @Operation(summary = "메뉴 삭제 API", description = "메뉴를 삭제하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메뉴 삭제 성공"),
            ApiResponse(responseCode = "403", description = "권한 없음"),
            ApiResponse(responseCode = "404", description = "메뉴를 찾을 수 없음")
        ]
    )
    fun deleteMenu(
        @PathVariable("store_id") storeId: Long,
        @PathVariable("menu_id") menuId: Long
    ): ResponseEntity<String> {
        menuService.deleteMenu(menuId)
        return ResponseEntity.ok("메뉴 삭제 성공")
    }
}
