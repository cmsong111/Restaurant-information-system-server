package com.galaxy.Restaurantinformationsystem.Repository;

import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, String> {
}
