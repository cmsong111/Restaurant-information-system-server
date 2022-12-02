package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findByLocation1AndLocation2(String location1, String location2);

    List<StoreEntity> findByNameAndLocation1AndLocation2(String Name, String Location1, String location2);

    StoreEntity findByNameAndLocation2(String Name, String location2);

    List<StoreEntity> findByNameContainingAndLocation1AndLocation2(String name, String location1, String location2);

    ArrayList<StoreEntity> findAllByCategoryAndLocation1AndLocation2(String category, String location1, String location2);

    ArrayList<StoreEntity> findByCategoryAndLocation1AndLocation2AndKidsAndPriceAndTastyAndRoleModel( String category, String location1, String location2, boolean kids, boolean price, boolean tasty, boolean roleModel);

    List<StoreEntity> findByAdminUser_UPK(Long UPK);


}


