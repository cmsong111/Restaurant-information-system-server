package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Service.StoreService;
import com.galaxy.Restaurantinformationsystem.common.FoodType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(name = "Store", description = "Store API")
@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private StoreService storeService;

    @PostMapping
    @Operation(summary = "가게 생성 API", description = "새로운 가게를 등록하는 API")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO) {
        return ResponseEntity.ok(storeService.createStoreDTO(storeDTO));
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Operation(summary = "가게 정보 수정 API", description = "가게 정보를 수정하는 API")
    public StoreDTO updateStore(
            @Parameter(description = "가게 ID", in = ParameterIn.PATH) @PathVariable("id") Long id, @RequestBody StoreDTO storeDTO
    ) {
        log.info("id" + id);
        return storeService.updateStoreDTO(storeDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @Operation(summary = "가게 삭제 API", description = "가게를 삭제하는 API")
    public String deleteStore(
            @Parameter(description = "삭제하려는 가게 ID", in = ParameterIn.PATH) @PathVariable("id") Long id,
            @Parameter(description = "유저 헤더", in = ParameterIn.HEADER) @RequestHeader(value = "Authorization") String token) {
        storeService.deleteStore(id, token);
        return "delete Requested";
    }

    @GetMapping("/{id}")
    @Operation(summary = "특정 가게 조회 API", description = "ID를 통해 가게를 조회하는 API")
    public ResponseEntity<StoreDTO> getStore(@PathVariable("id") Long id) {
        Optional<StoreDTO> storeDTO = storeService.searchById(id);
        return storeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "근처 가게 ", description = "특정 지역 근처의 가게를 조회하는 API")
    public List<StoreDTO> searchByLocation(
            @Parameter(description = "latitude 위도", example = "35.1665") @RequestParam Double latitude,
            @Parameter(description = "longitude 경도", example = "129.0477") @RequestParam Double longitude) {
        return storeService.getStore(latitude, longitude);
    }

    @GetMapping("/random")
    @Operation(summary = "랜덤 가게 조회 API", description = "특정 지역 근처의 랜덤으로 가게를 조회하는 API")
    public ResponseEntity<StoreDTO> getRandomStore(
            @Parameter(description = "latitude 위도", example = "35.1665") @RequestParam Double latitude,
            @Parameter(description = "longitude 경도", example = "129.0477") @RequestParam Double longitude) {
        Optional<StoreDTO> storeDTO = storeService.getRandomStore(latitude, longitude);
        return storeDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name")
    @Operation(summary = "가게 이름으로 조회 API", description = "가게 이름으로 가게를 조회하는 API")
    public ResponseEntity<List<StoreDTO>> searchByName(
            @RequestParam String name,
            @Parameter(description = "latitude 위도", example = "35.1665") @RequestParam Double latitude,
            @Parameter(description = "longitude 경도", example = "129.0477") @RequestParam Double longitude) {
        return ResponseEntity.ok(storeService.getStoreByName(name, latitude, longitude));
    }

    @GetMapping("/category")
    public ResponseEntity<List<StoreDTO>> searchByCategory(
            @RequestParam FoodType category,
            @Parameter(description = "latitude 위도", example = "35.1665") @RequestParam Double latitude,
            @Parameter(description = "longitude 경도", example = "129.0477") @RequestParam Double longitude) {
        return ResponseEntity.ok(storeService.getStoreByCategory(category, latitude, longitude));
    }


    @GetMapping("/myStore")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public List<StoreDTO> searchByUPK(
            @RequestHeader(value = "Authorization") String token) {
        return storeService.findMyStore(token);
    }
}

