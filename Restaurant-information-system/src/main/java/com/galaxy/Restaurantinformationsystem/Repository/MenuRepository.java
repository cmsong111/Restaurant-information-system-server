package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

    @Query("select m from MenuEntity m where m.store.id = ?1")
    ArrayList<MenuEntity> findByStore_SPK(Long id);

    List<MenuEntity> deleteByStore(StoreEntity store);

}
