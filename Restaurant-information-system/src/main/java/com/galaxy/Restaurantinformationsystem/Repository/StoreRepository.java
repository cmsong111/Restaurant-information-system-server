package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findByLocation1AndLocation2(String location1, String location2);

    List<StoreEntity> findByNameAndLocation1AndLocation2(String Name, String Location1, String location2);

    StoreEntity findByNameAndLocation2(String Name, String location2);

    List<StoreEntity> findByNameContainingAndLocation1AndLocation2(String name, String location1, String location2);

    ArrayList<StoreEntity> findAllByCategoryAndLocation1AndLocation2(String category, String location2, String name);

    ArrayList<StoreEntity> findByCategoryAndLocation1AndLocation2AndKidsAndPriceAndTastyAndRoleModel( String category, String location1, String location2, boolean kids, boolean price, boolean tasty, boolean roleModel);

    //ArrayList<StoreEntity> findAllByNameContainsAndLocation1AAndLocation2AndCategoryAndKidsAndPriceAndRoleModelOrderByTasty(String name, String locaion1, String locaiont2, String category, boolean kids, boolean price, boolean Rolemodel, boolean Tasty);
}


