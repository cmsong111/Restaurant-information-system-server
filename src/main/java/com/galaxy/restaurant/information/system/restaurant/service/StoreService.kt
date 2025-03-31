package com.galaxy.restaurant.information.system.restaurant.service

import com.galaxy.restaurant.information.system.restaurant.controller.request.RestaurantCreateForm
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreResponseDto
import com.galaxy.restaurant.information.system.restaurant.controller.response.StoreSummary
import com.galaxy.restaurant.information.system.restaurant.entity.FoodType
import com.galaxy.restaurant.information.system.restaurant.repository.MenuRepository
import com.galaxy.restaurant.information.system.restaurant.repository.RestaurantRepository
import com.galaxy.restaurant.information.system.restaurant.repository.ReviewRepository
import com.galaxy.restaurant.information.system.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StoreService(
    private var restaurantRepository: RestaurantRepository,
    private var userRepository: UserRepository,
    private var menuRepository: MenuRepository,
    private var reviewRepository: ReviewRepository,
    private var storeMapper: StoreMapper,
) {
    /**
     * 가게를 등록합니다.
     * @param storeRequestDto 가게 등록 요청 DTO
     * @param username 사용자 이름
     * @return 저장된 가게 정보
     */
    fun createStoreDTO(storeRequestDto: RestaurantCreateForm, username: String): StoreResponseDto {
        val storeEntity = storeMapper.toStoreEntity(storeRequestDto)
        storeEntity.admin = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        return storeMapper.toStoreResponseDto(restaurantRepository.save(storeEntity))
    }

    /**
     * 가게 정보를 수정합니다.
     * @param storeRequestDto 가게 수정 요청 DTO
     * @param storeId 가게 id
     * @return 수정된 가게 정보
     */
    fun updateStoreDTO(storeRequestDto: RestaurantCreateForm, storeId: Long): StoreResponseDto {
        val storeEntity = restaurantRepository.findById(storeId).orElseThrow()
        storeEntity.name = storeRequestDto.name
        storeEntity.location = storeRequestDto.location
        storeEntity.callNumber = storeRequestDto.callNumber
        storeEntity.category = storeRequestDto.category
        storeEntity.latitude = storeRequestDto.latitude
        storeEntity.longitude = storeRequestDto.longitude
        storeEntity.description = storeRequestDto.description
        return storeMapper.toStoreResponseDto(restaurantRepository.save(storeEntity))
    }


    /**
     * 가게를 삭제합니다.
     * @param id 가게 id
     */
    @Transactional
    fun deleteStore(id: Long) {
        val storeEntity = restaurantRepository.findById(id).orElseThrow()
        restaurantRepository.delete(storeEntity)
    }

    /**
     * 가게를 id로 검색합니다.
     * @param id 가게 id
     * @return 가게 정보
     * @throws IllegalArgumentException 가게를 찾을 수 없을 때
     * @author 김남주
     */
    fun searchById(id: Long): StoreResponseDto {
        val storeEntity = restaurantRepository.findById(id).orElseThrow { IllegalArgumentException("가게를 찾을 수 없습니다.") }
        return storeMapper.toStoreResponseDto(storeEntity)
    }

    /**
     * 특정 지역 근처의 가게를 조회합니다.
     * @param latitude 위도
     * @param longitude 경도
     * @return 가게 정보 리스트
     */
    fun getStore(latitude: Double?, longitude: Double?, name: String?, category: FoodType?): List<StoreSummary> {


        val storeEntityList = restaurantRepository.findStore(name, category, latitude, longitude)

        val storeDTOList: MutableList<StoreSummary> = ArrayList<StoreSummary>()

        for (storeEntity in storeEntityList) {
            storeDTOList.add(storeMapper.toStoreHeaderDto(storeEntity))
        }

        return storeDTOList
    }

    /**
     * 특정 지역 근처의 랜덤으로 가게를 조회합니다.
     * @param latitude 위도
     * @param longitude 경도
     * @return 가게 정보
     */
    fun getRandomStore(latitude: Double?, longitude: Double?): StoreResponseDto {
        val store = restaurantRepository.findRandomStore(latitude, longitude)
        return storeMapper.toStoreResponseDto(store)
    }


    /**
     * 내 가게를 조회합니다.
     *
     * @param username 사용자 이름
     * @return 가게 정보 리스트
     */
    fun findMyStore(username: String): List<StoreSummary> {
        val storeEntityList = restaurantRepository.findByAdminEmail(username)
        val storeDTOList: MutableList<StoreSummary> = ArrayList<StoreSummary>()

        for (storeEntity in storeEntityList) {
            storeDTOList.add(storeMapper.toStoreHeaderDto(storeEntity))
        }
        return storeDTOList
    }

}
