package com.galaxy.restaurant.information.system.controller

import com.galaxy.restaurant.information.system.dto.MenuRequestDto
import com.galaxy.restaurant.information.system.dto.MenuResponseDto
import com.galaxy.restaurant.information.system.service.MenuService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URI

@Tag(name = "메뉴 API", description = "메뉴 정보를 조회하는 API")
@RestController
@RequestMapping("/store/{store_id}/menu")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping
    @Operation(summary = "메뉴 조회 API", description = "메뉴를 조회하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메뉴 조회 성공", content = [Content(schema = Schema(implementation = List::class))]),
        ]
    )
    fun getMenu(@PathVariable("store_id") storeId: Long): List<MenuResponseDto> {
        return menuService.getMenuDTO(storeId)
    }

    @PostMapping
    @PreAuthorize("@checker.isStoreOwner(#store_id)")
    @Operation(summary = "메뉴 생성 API", description = "메뉴를 생성하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "메뉴 생성 성공"),
            ApiResponse(responseCode = "403", description = "권한 없음", content = [Content(schema = Schema(hidden = true))])
        ]
    )
    fun createMenu(
        @PathVariable("store_id") storeId: Long,
        @RequestBody menuRequestDto: MenuRequestDto
    ): ResponseEntity<MenuResponseDto> {
        val menuResponseDto = menuService.createMenuDTO(storeId, menuRequestDto)
        return ResponseEntity.created(URI.create("/menu/${menuResponseDto.id}")).body(menuResponseDto)
    }

    @PatchMapping("/{menu_id}")
    @PreAuthorize("@checker.isStoreOwner(#store_id)")
    @Operation(summary = "메뉴 수정 API", description = "메뉴를 수정하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메뉴 수정 성공"),
            ApiResponse(responseCode = "403", description = "권한 없음", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "404", description = "메뉴를 찾을 수 없음", content = [Content(schema = Schema(hidden = true))])
        ]
    )
    fun updateMenu(
        @RequestBody menuRequestDto: MenuRequestDto,
        @PathVariable("store_id") storeId: Long,
        @PathVariable("menu_id") menuId: Long
    ): MenuResponseDto {
        return menuService.updateMenuDTO(storeId, menuRequestDto, menuId)
    }


    @DeleteMapping("/{menu_id}")
    @PreAuthorize("@checker.isStoreOwner(#store_id)")
    @Operation(summary = "메뉴 삭제 API", description = "메뉴를 삭제하는 API")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "메뉴 삭제 성공"),
            ApiResponse(responseCode = "403", description = "권한 없음", content = [Content(schema = Schema(hidden = true))]),
            ApiResponse(responseCode = "404", description = "메뉴를 찾을 수 없음", content = [Content(schema = Schema(hidden = true))])
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
