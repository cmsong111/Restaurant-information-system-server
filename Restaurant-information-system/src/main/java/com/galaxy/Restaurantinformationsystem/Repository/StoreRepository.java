package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.common.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    List<StoreEntity> findByLatitudeBetweenAndLongitudeBetween(Double latitudeStart, Double latitudeEnd, Double longitudeStart, Double longitudeEnd);

    List<StoreEntity> findByLatitudeBetweenAndLongitudeBetweenAndNameLike(Double latitudeStart, Double latitudeEnd, Double longitudeStart, Double longitudeEnd, String name);

    List<StoreEntity> findByLatitudeBetweenAndLongitudeBetweenAndCategory(Double latitudeStart, Double latitudeEnd, Double longitudeStart, Double longitudeEnd, FoodType category);

    List<StoreEntity> findByAdmin_Id(Long id);


}
