package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
import com.galaxy.Restaurantinformationsystem.Repository.ReviewRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    UserRepository userRepository;
    StoreRepository storeRepository;
    ReviewRepository reviewRepository;

    public UserService(UserRepository userRepository, StoreRepository storeRepository, ReviewRepository reviewRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.reviewRepository = reviewRepository;
    }


    public UserDTO createUser(UserDTO user) {
        UserEntity userEntity = toEntity(user);
        return toDTO(userRepository.save(userEntity));
    }


    public UserDTO loginUser(String id, String password) {
        UserEntity userEntity = userRepository.readbylogin(id, password);
        System.out.println(userEntity.toString());
        return toDTO(userEntity);
    }

    public UserDTO deleteUser(UserDTO user) {
        UserEntity userEntity = userRepository.findById(user.getId()).get();

        // 자기 가게 삭제
        List<StoreEntity> stores = storeRepository.findByAdminUser_UPK(user.getId());
        if (stores!= null) {
            for (StoreEntity store : stores) {
                storeRepository.delete(store);
            }
        }
        // 본인이 작성한 리뷰 삭제
        List<ReviewEntity> reivews = reviewRepository.findByUserEntity_UPK(user.getId());
        if (reivews != null){
            for (ReviewEntity review : reivews) {
                reviewRepository.delete(review);
            }
        }

        userRepository.delete(userEntity);
        return null;
    }

    public UserDTO updateUser(UserDTO user) {
        return toDTO(userRepository.save(toEntity(user)));
    }

    public UserDTO toDTO(UserEntity userEntity) {

        List<Long> storeDTOArrayList = new ArrayList<>();
        if (userEntity.getStoreEntity() != null) {
            for (StoreEntity store : userEntity.getStoreEntity()) {
                storeDTOArrayList.add(store.getSPK());
            }
        }
        List<Long> reviewDTOs = new ArrayList<>();
        if (userEntity.getReviewEntity() != null) {
            for (ReviewEntity review : userEntity.getReviewEntity()) {
                reviewDTOs.add(review.getRPK());
            }
        }
        return UserDTO.builder()
                .UPK(userEntity.getUPK())
                .ID(userEntity.getID())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .age(userEntity.getAge())
                .isAdmin(userEntity.isAdmin())
                .SPK(storeDTOArrayList)
                .RPK(reviewDTOs)
                .build();
    }

    public UserEntity toEntity(UserDTO userDTO) {

        List<StoreEntity> storeEntityList = new ArrayList<>();
        if (userDTO.getSPK() != null) {
            for (Long SPK : userDTO.getSPK()) {
                storeEntityList.add(storeRepository.findById(SPK).get());

            }
        }
        List<ReviewEntity> ReviewEntities = new ArrayList<>();
        if (userDTO.getRPK() != null) {
            for (Long RPK : userDTO.getRPK()) {
                ReviewEntities.add(reviewRepository.findById(RPK).get());
            }
        }
        return UserEntity.builder()
                .UPK(userDTO.getUPK())
                .ID(userDTO.getID())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .age(userDTO.getAge())
                .isAdmin(userDTO.isAdmin())
                .storeEntity(storeEntityList)
                .reviewEntity(ReviewEntities)
                .build();
    }
}
