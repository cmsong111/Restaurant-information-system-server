package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.DataAPI.*;
import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import com.galaxy.Restaurantinformationsystem.Repository.ReviewRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    Gson gson = new Gson();
    StoreRepository storeRepository;
    UserRepository userRepository;
    MenuRepository menuRepository;
    ReviewRepository reviewRepository;
    HttpAPI httpAPI;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StoreService(StoreRepository storeRepository, MenuRepository menuRepository, HttpAPI httpAPI, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.httpAPI = httpAPI;
    }


    public StoreDTO createStoreDTO(StoreDTO storeDTO) {
        StoreEntity storeEntity = toEntity(storeDTO);

        return toDTO(storeRepository.save(storeEntity));
    }

    public Optional<StoreDTO> serchById(Long id) {
        return Optional.ofNullable(toDTO(storeRepository.findById(id).get()));
    }

    public StoreDTO serchByname(StoreDTO storeDTO) {
        return null;
    }

    public StoreDTO serchByCategory(Long storeDTO) {
        return null;
    }


    public ArrayList<StoreDTO> searchByLocationArray(String location1, String location2) {
        ArrayList<StoreDTO> results = new ArrayList<>();

        List<StoreEntity> storeEntityArrayList = storeRepository.findByLocation1AndLocation2(location1, location2);

        if (storeEntityArrayList != null) {
            for (StoreEntity storeEntity : storeEntityArrayList) {
                logger.info("store:" + storeEntity.toString());
                results.add(toDTO(storeEntity));
            }
        }
        return results;
    }

    public StoreDTO updateStoreDTO(StoreDTO storeDTO) {

        ArrayList<MenuEntity> menuEntities = new ArrayList<>();
        if (storeDTO.getMenus() != null) {
            for (Long menu : storeDTO.getMenus()) {
                menuEntities.add(menuRepository.findById(menu).get());
            }
        }

        ArrayList<ReviewEntity> reviews = new ArrayList<>();
        if (storeDTO.getReviews() != null) {
            for (Long review : storeDTO.getReviews()) {
                reviews.add(reviewRepository.findById(review).get());
            }
        }

        StoreEntity storeEntity = storeRepository.findById(storeDTO.getSPK()).get();
        storeEntity.setKids(storeDTO.isKids());
        storeEntity.setTasty(storeDTO.isTasty());
        storeEntity.setPrice(storeDTO.isPrice());
        storeEntity.setCall(storeDTO.getCall());
        storeEntity.setRoleModel(storeDTO.isRoleModel());
        if (storeDTO.getCategory() != null) {
            storeEntity.setCategory(storeDTO.getCategory());
        }
        storeEntity.setLocation1(storeDTO.getLocation1());
        storeEntity.setLocation2(storeDTO.getLocation2());
        storeEntity.setLocation3(storeDTO.getLocation3());
        storeEntity.setStartTime(storeDTO.getStartTime());
        storeEntity.setEndTime(storeDTO.getEndTime());
        storeEntity.setName(storeDTO.getName());
        storeEntity.setLocationX(storeEntity.getLocationX());
        storeEntity.setLocationY(storeEntity.getLocationY());
        storeEntity.setAdminUser(storeRepository.findById(storeDTO.getUPK()).get().getAdminUser());
        storeEntity.setMenus(menuEntities);
        storeEntity.setReviews(reviews);

        return toDTO(storeRepository.save(storeEntity));
    }


    public void deleteStore(StoreDTO storeDTO) {
        StoreEntity storeEntity = storeRepository.findById(storeDTO.getSPK()).get();
        storeRepository.delete(storeEntity);
    }

    public ArrayList<StoreDTO> serchByNameAndLocation(String location1, String location2, String name) {
        ArrayList<StoreDTO> results = new ArrayList<>();
        List<StoreEntity> queried = storeRepository.findByNameAndLocation1AndLocation2(location1, location2, name);
        if (queried != null) {
            for (StoreEntity object : queried) {
                results.add(toDTO(object));
            }
        }
        return results;
    }

    public ArrayList<StoreDTO> serchByCategoryAndLocation2(String category, String location1, String location2) {
        ArrayList<StoreDTO> results = new ArrayList<>();
        List<StoreEntity> queried = storeRepository.findAllByCategoryAndLocation1AndLocation2(category, location1, location2);
        if (queried != null) {
            for (StoreEntity object : queried) {
                results.add(toDTO(object));
            }
        }
        return results;
    }


    public boolean storeDuplicationCheck(StoreDTO storeDTO) {
        List<StoreEntity> result = storeRepository.findByNameAndLocation1AndLocation2(storeDTO.getName(), storeDTO.getLocation1(), storeDTO.getLocation2());
        if (result.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

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
                    .UPK(1L)
                    .locationY(Double.valueOf(i.get경도()))
                    .locationX(Double.valueOf(i.get위도()))
                    .location1(location[0])
                    .location2(location[1])
                    .location3(location[2])
                    .category(i.get업종())
                    .reviews(null)
                    .build();
            if (storeDuplicationCheck(object)) {
                storeRepository.save(toEntity(object));
            }

        }
    }

    public void updateTasty(int perPage, int page) throws IOException, URISyntaxException {
        String result = httpAPI.getTastyStore(perPage, page);
        ArrayList<StoreTastyItem> data = gson.fromJson(result, StoreTastyPage.class).getFoodKr.item;

        for (StoreTastyItem item : data) {
            System.out.println(item.toString());
        }
    }


    public StoreDTO toDTO(StoreEntity storeEntity) {
        List<Long> reviewDTOS = new ArrayList<>();
        if (storeEntity.getReviews() != null) {
            for (ReviewEntity review : storeEntity.getReviews()) {
                reviewDTOS.add(review.getRPK());
            }
        }

        List<Long> menuDTOs = new ArrayList<>();
        if (storeEntity.getMenus() != null) {
            for (MenuEntity menu : storeEntity.getMenus()) {
                menuDTOs.add(menu.getMPK());
            }
        }


        return StoreDTO.builder()
                .SPK(storeEntity.getSPK())
                .name(storeEntity.getName())
                .call(storeEntity.getCall())
                .location1(storeEntity.getLocation1())
                .location2(storeEntity.getLocation2())
                .location3(storeEntity.getLocation3())
                .startTime(storeEntity.getStartTime())
                .endTime(storeEntity.getEndTime())
                .kids(storeEntity.isKids())
                .price(storeEntity.isPrice())
                .category(storeEntity.getCategory())
                .tasty(storeEntity.isTasty())
                .roleModel(storeEntity.isRoleModel())
                .locationX(storeEntity.getLocationX())
                .locationY(storeEntity.getLocationY())
                .UPK(storeEntity.getAdminUser().getUPK())
                .menus(menuDTOs)
                .reviews(reviewDTOS)
                .build();
    }

    public StoreEntity toEntity(StoreDTO storeDTO) {
        List<ReviewEntity> reviewEntities = new ArrayList<>();
        if (storeDTO.getReviews() != null) {
            for (Long RPK : storeDTO.getReviews()) {
                reviewEntities.add(reviewRepository.findById(RPK).get());
            }
        }
        List<MenuEntity> menuEntities = new ArrayList<>();
        if (storeDTO.getMenus() != null) {

            for (Long MPK : storeDTO.getMenus()) {
                menuEntities.add(menuRepository.findById(MPK).get());
            }
        }

        return StoreEntity.builder()
                .SPK(storeDTO.getSPK())
                .name(storeDTO.getName())
                .call(storeDTO.getCall())
                .location1(storeDTO.getLocation1())
                .location2(storeDTO.getLocation2())
                .location3(storeDTO.getLocation3())
                .startTime(storeDTO.getStartTime())
                .endTime(storeDTO.getEndTime())
                .kids(storeDTO.isKids())
                .price(storeDTO.isPrice())
                .category(storeDTO.getCategory())
                .tasty(storeDTO.isTasty())
                .roleModel(storeDTO.isRoleModel())
                .locationX(storeDTO.getLocationX())
                .locationY(storeDTO.getLocationY())
                .adminUser(userRepository.findById(storeDTO.getUPK()).get())
                .reviews(reviewEntities)
                .menus(menuEntities)
                .build();

    }
}
