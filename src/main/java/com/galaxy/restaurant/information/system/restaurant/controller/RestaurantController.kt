package com.galaxy.restaurant.information.system.restaurant.controller

import com.galaxy.restaurant.information.system.restaurant.controller.request.RestaurantCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreResponseDto
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreSummary
import com.galaxy.restaurant.information.system.restaurant.entity.RestaurantType
import com.galaxy.restaurant.information.system.restaurant.service.StoreService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import java.net.URI
import java.security.Principal
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@Tag(name = "가게 API", description = "가게 정보를 조회하는 API")
@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantController(
    private val storeService: StoreService
) {
    @GetMapping
    @Operation(
        summary = "가게 검색 API ",
        description = """# 가게를 검색하는 API입니다.
아래 파라미터는 모두 선택적입니다.""",
    )
    fun searchByLocation(
        @Parameter(description = "latitude 위도", example = "35.1665") @RequestParam(required = false) latitude: Double?,
        @Parameter(description = "longitude 경도", example = "129.0477") @RequestParam(required = false) longitude: Double?,
        @Parameter(description = "이름 검색") @RequestParam(required = false) name: String?,
        @Parameter(description = "음식 종류") @RequestParam(required = false) type: RestaurantType?,
        @ParameterObject pageable: Pageable,
    ): List<StoreSummary> {
        return storeService.getStore(latitude, longitude, name, type)
    }

    @GetMapping("/{restaurantId}")
    @Operation(summary = "특정 가게 조회 API", description = "ID를 통해 가게를 조회하는 API")
    fun getStore(
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
    ): ResponseEntity<StoreResponseDto> {
        val store = storeService.searchById(restaurantId)
        return ResponseEntity.ok(store)
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun createStore(
        @RequestBody storeRequestDto: RestaurantCreateForm,
        @AuthenticationPrincipal principal: Principal,
    ): ResponseEntity<StoreResponseDto> {
        val storeDTO = storeService.createStoreDTO(storeRequestDto, principal.name)
        return ResponseEntity.created(URI.create("/store/" + storeDTO.id)).body(storeDTO)
    }


    @PatchMapping("/{restaurantId}")
    @PreAuthorize("@restaurantRoleChecker.isAdmin(#restaurantId,#principal.name)")
    @Operation(summary = "가게 정보 수정 API", description = "가게 정보를 수정하는 API")
    fun updateStore(
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
        @Parameter(description = "수정 정보") @RequestBody storeRequestDto: RestaurantCreateForm,
        @AuthenticationPrincipal principal: Principal,
    ): ResponseEntity<StoreResponseDto> {
        return ResponseEntity.ok(storeService.updateStoreDTO(storeRequestDto, restaurantId))
    }

    @DeleteMapping("/{restaurantId}")
    @PreAuthorize("@restaurantRoleChecker.isAdmin(#restaurantId,#principal.name)")
    @Operation(summary = "가게 삭제 API", description = "가게를 삭제하는 API(가게 주인만 가능)")
    fun deleteStore(
        @PathVariable @Parameter(description = "가게 ID") restaurantId: Long,
        @AuthenticationPrincipal principal: Principal
    ): ResponseEntity<Unit> {
        storeService.deleteStore(restaurantId)
        return ResponseEntity.noContent().build()
    }
}
