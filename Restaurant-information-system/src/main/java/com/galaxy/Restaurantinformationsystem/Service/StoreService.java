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

        if (storeEntity.isKids() != storeDTO.isKids()) {
            storeEntity.setKids(storeDTO.isKids());
        }
        if (storeEntity.isTasty() != storeDTO.isTasty()) {
            storeEntity.setTasty(storeDTO.isTasty());
        }
        if (storeEntity.isPrice() != storeDTO.isPrice()) {
            storeEntity.setPrice(storeDTO.isPrice());
        }
        if (storeEntity.isRoleModel() != storeDTO.isRoleModel()) {
            storeEntity.setRoleModel(storeDTO.isRoleModel());
        }
        if (storeDTO.getCall() != null) {
            storeEntity.setCall(storeDTO.getCall());
        }
        if (storeDTO.getCategory() != null) {
            storeEntity.setCategory(storeDTO.getCategory());
        }
        if (storeDTO.getLocation1() != null) {
            storeEntity.setLocation1(storeDTO.getLocation1());
        }
        if (storeDTO.getLocation2() != null) {
            storeEntity.setLocation2(storeDTO.getLocation2());
        }
        if (storeDTO.getLocation3() != null) {
            storeEntity.setLocation3(storeDTO.getLocation3());
        }
        if (storeDTO.getStartTime() != null) {
            storeEntity.setStartTime(storeDTO.getStartTime());
        }
        if (storeDTO.getEndTime() != null) {
            storeEntity.setEndTime(storeDTO.getEndTime());
        }
        if (storeDTO.getName() != null) {
            storeEntity.setName(storeDTO.getName());
        }
        if (storeDTO.getLocationX() != 0) {
            storeEntity.setLocationX(storeEntity.getLocationX());
        }
        if (storeDTO.getLocationY() != 0) {
            storeEntity.setLocationY(storeEntity.getLocationY());
        }
        if (storeDTO.getUPK() != 0) {
            storeEntity.setAdminUser(storeRepository.findById(storeDTO.getUPK()).get().getAdminUser());
        }
        if (!menuEntities.isEmpty()) {
            storeEntity.setMenus(menuEntities);
        }
        if (!reviews.isEmpty()) {
            storeEntity.setReviews(reviews);
        }

        return toDTO(storeRepository.save(storeEntity));
    }


    public void deleteStore(StoreDTO storeDTO) {
        StoreEntity storeEntity = storeRepository.findById(storeDTO.getSPK()).get();
        storeRepository.delete(storeEntity);
    }

    public ArrayList<StoreDTO> serchByNameAndLocation(String location1, String location2, String name) {
        ArrayList<StoreDTO> results = new ArrayList<>();
        List<StoreEntity> queried = storeRepository.findByNameLikeAndLocation1AndLocation2(name, location1, location2);
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
            if (location.length > 2) {
                for (int lo = 3; lo < location.length; lo++) {
                    location[2] = location[2] + location[lo];
                }
            }
            // 객체 변환
            StoreDTO object = StoreDTO.builder()
                    .kids(true)
                    .name(i.get가맹점명칭())
                    .call(i.get전화번호())
                    .UPK(1L)
                    .locationY(Double.valueOf(i.get위도()))
                    .locationX(Double.valueOf(i.get경도()))
                    .location1(location[0])
                    .location2(location[1])
                    .location3(location[2])
                    .category(i.get업종())
                    .reviews(null)
                    .build();
            // 저장 및 업데이트
            if (storeDuplicationCheck(object)) {
                this.createStoreDTO(object);
            } else {
                this.updateStoreDTO(object);
            }
        }
    }

    public void updateTasty(int perPage, int page) throws IOException, URISyntaxException {
        String result = httpAPI.getTastyStore(perPage, page);
        ArrayList<StoreTastyItem> data = gson.fromJson(result, StoreTastyPage.class).getFoodKr.item;

        for (StoreTastyItem item : data) {
            // 주소 파싱
            String[] location = item.getADDR1().split(" ");
            if (location.length > 2) {
                for (int lo = 3; lo < location.length; lo++) {
                    location[2] = location[2] + location[lo];
                }
            }
            //객체 생성
            StoreDTO storeDTO = StoreDTO.builder()
                    .name(item.getMAIN_TITLE())
                    .locationX(item.getLNG())
                    .locationY(item.getLAT())
                    .location1(location[0])
                    .location2(location[1])
                    .location3(location[2])
                    .category(item.getCNTCT_TEL())
                    .build();

            // 저장 및 업데이트
            if (storeDuplicationCheck(storeDTO)) {
                this.createStoreDTO(storeDTO);
            } else {
                this.updateStoreDTO(storeDTO);
            }
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
