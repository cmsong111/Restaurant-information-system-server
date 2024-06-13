package com.galaxy.Restaurantinformationsystem.service

import com.galaxy.Restaurantinformationsystem.common.FoodType
import com.galaxy.Restaurantinformationsystem.dto.StoreHeaderDto
import com.galaxy.Restaurantinformationsystem.dto.StoreRequestDto
import com.galaxy.Restaurantinformationsystem.dto.StoreResponseDto
import com.galaxy.Restaurantinformationsystem.mapper.StoreMapper
import com.galaxy.Restaurantinformationsystem.repository.MenuRepository
import com.galaxy.Restaurantinformationsystem.repository.ReviewRepository
import com.galaxy.Restaurantinformationsystem.repository.StoreRepository
import com.galaxy.Restaurantinformationsystem.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class StoreService(
    var storeRepository: StoreRepository,
    var userRepository: UserRepository,
    var menuRepository: MenuRepository,
    var reviewRepository: ReviewRepository,
    var storeMapper: StoreMapper,
) {
    /**
     * 가게를 등록합니다.
     * @param storeRequestDto 가게 등록 요청 DTO
     * @param username 사용자 이름
     * @return 저장된 가게 정보
     */
    fun createStoreDTO(storeRequestDto: StoreRequestDto, username: String): StoreResponseDto {
        val storeEntity = storeMapper.toStoreEntity(storeRequestDto)
        storeEntity.admin = userRepository.findByEmail(username) ?: throw RuntimeException("가입되지 않은 유저입니다.")
        return storeMapper.toStoreResponseDto(storeRepository.save(storeEntity))
    }

    /**
     * 가게 정보를 수정합니다.
     * @param storeRequestDto 가게 수정 요청 DTO
     * @param storeId 가게 id
     * @return 수정된 가게 정보
     */
    fun updateStoreDTO(storeRequestDto: StoreRequestDto, storeId: Long): StoreResponseDto {
        val storeEntity = storeRepository.findById(storeId).orElseThrow()
        storeEntity.name = storeRequestDto.name
        storeEntity.location = storeRequestDto.location
        storeEntity.callNumber = storeRequestDto.callNumber
        storeEntity.category = storeRequestDto.category
        storeEntity.latitude = storeRequestDto.latitude
        storeEntity.longitude = storeRequestDto.longitude
        storeEntity.description = storeRequestDto.description
        return storeMapper.toStoreResponseDto(storeRepository.save(storeEntity))
    }


    /**
     * 가게를 삭제합니다.
     * @param id 가게 id
     */
    @Transactional
    fun deleteStore(id: Long) {
        val storeEntity = storeRepository.findById(id).orElseThrow()
        storeRepository.delete(storeEntity)
    }

    /**
     * 가게를 id로 검색합니다.
     * @param id 가게 id
     * @return 가게 정보
     * @throws IllegalArgumentException 가게를 찾을 수 없을 때
     * @author 김남주
     */
    fun searchById(id: Long): StoreResponseDto {
        val storeEntity = storeRepository.findById(id).orElseThrow { IllegalArgumentException("가게를 찾을 수 없습니다.") }
        return storeMapper.toStoreResponseDto(storeEntity)
    }

    /**
     * 특정 지역 근처의 가게를 조회합니다.
     * @param latitude 위도
     * @param longitude 경도
     * @return 가게 정보 리스트
     */
    fun getStore(latitude: Double?, longitude: Double?, name: String?, category: FoodType?): List<StoreHeaderDto> {


        val storeEntityList = storeRepository.findStore(name, category, latitude, longitude)

        val storeDTOList: MutableList<StoreHeaderDto> = ArrayList<StoreHeaderDto>()

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
        val store = storeRepository.findRandomStore(latitude, longitude)
        return storeMapper.toStoreResponseDto(store)
    }


    /**
     * 내 가게를 조회합니다.
     *
     * @param username 사용자 이름
     * @return 가게 정보 리스트
     */
    fun findMyStore(username: String): List<StoreHeaderDto> {
        val storeEntityList = storeRepository.findByAdminEmail(username)
        val storeDTOList: MutableList<StoreHeaderDto> = ArrayList<StoreHeaderDto>()

        for (storeEntity in storeEntityList) {
            storeDTOList.add(storeMapper.toStoreHeaderDto(storeEntity))
        }
        return storeDTOList
    }

}
