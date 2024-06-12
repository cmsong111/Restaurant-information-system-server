package com.galaxy.Restaurantinformationsystem.repository;

import com.galaxy.Restaurantinformationsystem.entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    void deleteByStoreEntity(StoreEntity storeEntity);

    List<ReviewEntity> findByUserEntity_Id(Long id);

    void deleteByUserEntity(UserEntity userEntity);

}
