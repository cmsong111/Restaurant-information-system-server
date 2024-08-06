package com.galaxy.restaurant.information.system.controller

import com.galaxy.restaurant.information.system.common.FoodType
import com.galaxy.restaurant.information.system.dto.StoreHeaderDto
import com.galaxy.restaurant.information.system.dto.StoreRequestDto
import com.galaxy.restaurant.information.system.dto.StoreResponseDto
import com.galaxy.restaurant.information.system.service.StoreService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.security.Principal


@Tag(name = "가게 API", description = "가게 정보를 조회하는 API")
@RestController
@RequestMapping("/store")
class StoreController(
    private val storeService: StoreService
) {
    @PostMapping
    @Operation(summary = "가게 생성 API", description = "새로운 가게를 등록하는 API")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "가게 생성 성공",
                content = [Content(schema = Schema(implementation = StoreResponseDto::class))]
            ),
            ApiResponse(
                responseCode = "400",
                description = "잘못된 요청",
                content = [Content(schema = Schema(hidden = true))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "권한 없음",
                content = [Content(schema = Schema(hidden = true))]
            )]
    )
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun createStore(
        @RequestBody storeRequestDto: StoreRequestDto,
        principal: Principal
    ): ResponseEntity<StoreResponseDto> {
        val storeDTO = storeService.createStoreDTO(storeRequestDto, principal.name)
        return ResponseEntity.created(URI.create("/store/" + storeDTO.id)).body(storeDTO)
    }


    @PatchMapping("/{id}")
    @PreAuthorize("@checker.isStoreOwner(#id)")
    @Operation(summary = "가게 정보 수정 API", description = "가게 정보를 수정하는 API")
    fun updateStore(
        @Parameter(description = "가게 ID") @PathVariable("id") id: Long,
        @Parameter(description = "수정 정보") @RequestBody storeRequestDto: StoreRequestDto
    ): ResponseEntity<StoreResponseDto> {
        return ResponseEntity.ok(storeService.updateStoreDTO(storeRequestDto, id))
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@checker.isStoreOwner(#id)")
    @Operation(summary = "가게 삭제 API", description = "가게를 삭제하는 API(가게 주인만 가능)")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "가게 삭제 성공",
                content = [Content(schema = Schema(hidden = true))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "권한 없음",
                content = [Content(schema = Schema(hidden = true))]
            )]
    )
    fun deleteStore(
        @Parameter(description = "가게 ID") @PathVariable("id") id: Long
    ): String {
        storeService.deleteStore(id)
        return "delete Requested"
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 가게 조회 API", description = "ID를 통해 가게를 조회하는 API")
    fun getStore(@PathVariable("id") id: Long): ResponseEntity<StoreResponseDto> {
        val store = storeService.searchById(id)
        return ResponseEntity.ok(store)
    }

    @GetMapping
    @Operation(summary = "가게 검색 API ", description = "가게를 검색하는 API입니다.<br>아래 파라미터는 모두 선택적입니다.")
    fun searchByLocation(
        @Parameter(description = "latitude 위도", example = "35.1665") @RequestParam(required = false) latitude: Double?,
        @Parameter(description = "longitude 경도", example = "129.0477") @RequestParam(required = false) longitude: Double?,
        @Parameter(description = "이름 검색") @RequestParam(required = false) name: String?,
        @Parameter(description = "음식 종류") @RequestParam(required = false) category: FoodType?
    ): List<StoreHeaderDto> {
        return storeService.getStore(latitude, longitude, name, category)
    }

    @GetMapping("/random")
    @Operation(summary = "랜덤 가게 조회 API", description = "특정 지역 근처의 랜덤으로 가게를 조회하는 API")
    fun getRandomStore(
        @Parameter(description = "latitude 위도", example = "35.1665") @RequestParam(required = false) latitude: Double?,
        @Parameter(description = "longitude 경도", example = "129.0477") @RequestParam(required = false) longitude: Double?
    ): ResponseEntity<StoreResponseDto> {
        val storeResponseDto = storeService.getRandomStore(latitude, longitude)
        return ResponseEntity.ok(storeResponseDto)
    }


    @GetMapping("/myStore")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Operation(summary = "내 가게 조회 API", description = "내 가게를 조회하는 API")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "내 가게 조회 성공",
                content = [Content(schema = Schema(implementation = List::class))]
            ),
            ApiResponse(
                responseCode = "403",
                description = "권한 없음",
                content = [Content(schema = Schema(hidden = true))]
            )]
    )
    fun searchByUPK(
        principal: Principal
    ): ResponseEntity<List<StoreHeaderDto>> {
        return ResponseEntity.ok(storeService.findMyStore(principal.name))
    }

}
