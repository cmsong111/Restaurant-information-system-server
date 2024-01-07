package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import com.galaxy.Restaurantinformationsystem.Repository.ReviewRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import com.galaxy.Restaurantinformationsystem.common.FoodType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreService {
    StoreRepository storeRepository;
    UserRepository userRepository;
    MenuRepository menuRepository;
    ReviewRepository reviewRepository;
    ModelMapper modelMapper;

    public StoreDTO createStoreDTO(StoreDTO storeDTO) {
        StoreEntity storeEntity = modelMapper.map(storeDTO, StoreEntity.class);
        return modelMapper.map(storeRepository.save(storeEntity), StoreDTO.class);
    }

    public StoreDTO updateStoreDTO(StoreDTO storeDTO) {
        StoreEntity storeEntity = modelMapper.map(storeDTO, StoreEntity.class);
        return modelMapper.map(storeRepository.save(storeEntity), StoreDTO.class);
    }

    public Optional<StoreDTO> searchById(Long id) {
        Optional<StoreEntity> storeEntity = storeRepository.findById(id);
        return storeEntity.map(entity -> modelMapper.map(entity, StoreDTO.class));
    }

    public void deleteStore(Long id, String token) {
        StoreEntity storeEntity = storeRepository.findById(id).orElseThrow();

        // TODO: 관리가 아닌 유저가 삭제 요청을 보낼 경우 예외처리 필요


        // TODO: Casecade로 처리해야함
        storeEntity.setAdmin(null);
        menuRepository.deleteByStore(storeEntity);
        reviewRepository.deleteByStoreEntity(storeEntity);

        storeRepository.delete(storeEntity);
    }

    public List<StoreDTO> getStore(Double latitude, Double longitude) {
        List<StoreEntity> storeEntityList = storeRepository.findByLatitudeBetweenAndLongitudeBetween(latitude - 0.02, latitude + 0.02, longitude - 0.02, longitude + 0.02);
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (StoreEntity storeEntity : storeEntityList) {
            storeDTOList.add(modelMapper.map(storeEntity, StoreDTO.class));
        }
        return storeDTOList;
    }

    public Optional<StoreDTO> getRandomStore(Double latitude, Double longitude) {
        List<StoreEntity> storeEntityList = storeRepository.findByLatitudeBetweenAndLongitudeBetween(latitude - 0.02, latitude + 0.02, longitude - 0.02, longitude + 0.02);
        if (storeEntityList.isEmpty()) {
            return Optional.empty();
        }
        Collections.shuffle(storeEntityList);
        return Optional.of(modelMapper.map(storeEntityList.get(0), StoreDTO.class));
    }

    public List<StoreDTO> getStoreByName(String name, Double latitude, Double longitude) {
        List<StoreEntity> storeEntityList = storeRepository.findByLatitudeBetweenAndLongitudeBetweenAndNameLike(latitude - 0.02, latitude + 0.02, longitude - 0.02, longitude + 0.02, name);
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (StoreEntity storeEntity : storeEntityList) {
            storeDTOList.add(modelMapper.map(storeEntity, StoreDTO.class));
        }
        return storeDTOList;
    }

    public List<StoreDTO> getStoreByCategory(FoodType category, Double latitude, Double longitude) {
        List<StoreEntity> storeEntityList = storeRepository.findByLatitudeBetweenAndLongitudeBetweenAndCategory(latitude - 0.02, latitude + 0.02, longitude - 0.02, longitude + 0.02, category);
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (StoreEntity storeEntity : storeEntityList) {
            storeDTOList.add(modelMapper.map(storeEntity, StoreDTO.class));
        }
        return storeDTOList;
    }

    public List<StoreDTO> findMyStore(String token) {
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (StoreEntity storeEntity : storeRepository.findByAdmin_Id(1L)) {
            storeDTOList.add(modelMapper.map(storeEntity, StoreDTO.class));
        }
        return storeDTOList;
    }

}
