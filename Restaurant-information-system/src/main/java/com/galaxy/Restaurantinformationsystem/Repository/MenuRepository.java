package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

    @Query("select m from MenuEntity m where m.store.SPK = ?1")
    ArrayList<MenuEntity> findByStore_SPK(Long SPK);
}
