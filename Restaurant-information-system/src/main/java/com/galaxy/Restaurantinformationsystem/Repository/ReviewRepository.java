package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, String>{
}
