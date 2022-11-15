package com.galaxy.Restaurantinformationsystem.Service;


import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;

public interface StoreService {
    public StoreDTO createStoreDTO(StoreDTO storeDTO);

    public StoreDTO readStoreDTO(StoreDTO storeDTO);

    public StoreDTO updateStoreDTO(StoreDTO storeDTO);

    public void deleteStore(StoreDTO storeDTO);
}
