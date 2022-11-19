package com.galaxy.Restaurantinformationsystem.Service;


import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;

import java.io.IOException;
import java.net.URISyntaxException;

public interface StoreService {
    public StoreDTO createStoreDTO(StoreDTO storeDTO);

    public StoreDTO readStoreDTO(StoreDTO storeDTO);

    public StoreDTO updateStoreDTO(StoreDTO storeDTO);

    public void deleteStore(StoreDTO storeDTO);
    public void updateKids(int perPage, int page) throws IOException, URISyntaxException;
}
