package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import com.galaxy.Restaurantinformationsystem.DataAPI.HttpAPI;
import com.galaxy.Restaurantinformationsystem.DataAPI.StoreKidDataDTO;
import com.galaxy.Restaurantinformationsystem.DataAPI.StoreKidsPageDTO;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Service
public class StoreServiceImpl implements StoreService {

    Gson gson = new Gson();
    StoreRepository storeRepository;
    HttpAPI httpAPI;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, HttpAPI httpAPI) {
        this.storeRepository = storeRepository;
        this.httpAPI = httpAPI;
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

    @Override
    public void updateKids(int perPage, int page) throws IOException, URISyntaxException {

        String response = httpAPI.getKidsStore(perPage, page);
        // 객체 변환
        ArrayList<StoreKidDataDTO> data = gson.fromJson(response, StoreKidsPageDTO.class).getData();



        for (StoreKidDataDTO i : data) {
            String[] location = i.get도로명주소().split(" ");


            StoreDTO object = StoreDTO.builder()
                    .kids(true)
                    .name(i.get가맹점명칭())
                    .call(i.get전화번호())
                    .adminUser(UserDTO.builder().build())
                    .locationY(Double.valueOf(i.get경도()))
                    .locationX(Double.valueOf(i.get위도()))
                    .location1(location[0])
                    .location2(location[1])
                    .location3(location[2])
                    .category(i.get업종())
                    .build();

            storeRepository.save(object.toEntity());
        }
    }

}
