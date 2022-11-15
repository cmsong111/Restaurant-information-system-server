package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {


    StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreDTO createStoreDTO(StoreDTO storeDTO) {
        StoreEntity storeEntity = storeDTO.toEntity();

        return storeRepository.save(storeEntity).toDTO();
    }

    @Override
    public StoreDTO readStoreDTO(StoreDTO storeDTO) {
        // TODO : readStore 메소드 작성
        return null;
    }

    @Override
    public StoreDTO updateStoreDTO(StoreDTO storeDTO) {
        StoreEntity storeEntity = storeDTO.toEntity();
        return storeEntity.toDTO();
    }

    @Override
    public void deleteStore(StoreDTO storeDTO) {
        StoreEntity storeEntity = storeDTO.toEntity();
        storeRepository.delete(storeEntity);
    }
}
