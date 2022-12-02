package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("select m from ReviewEntity m where m.storeEntity.SPK = ?1")
    ArrayList<ReviewEntity> findByStore_SPK(Long SPK);

    List<ReviewEntity> findByUserEntity_UPK(Long UPK);


}
