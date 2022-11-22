package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    List<StoreEntity> findByLocation1AndLocation2(String location1, String location2);

    List<StoreEntity> findByNameAndLocation1AndLocation2(String Name, String Location1, String location2);

    List<StoreEntity> findByNameLikeAndLocation1AndLocation2(String name, String location1, String location2);



    ArrayList<StoreEntity> findAllByCategoryAndLocation1AndLocation2(String category, String location2, String name);

}


