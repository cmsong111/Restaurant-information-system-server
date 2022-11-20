package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findByLocation1AndLocation2(String location1, String location2);

    List<StoreEntity> findAllByNameAndLocation2(String Name, String location2);
}
