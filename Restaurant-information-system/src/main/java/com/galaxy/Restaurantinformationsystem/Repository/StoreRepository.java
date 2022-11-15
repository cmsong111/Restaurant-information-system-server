package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, String> {
}
