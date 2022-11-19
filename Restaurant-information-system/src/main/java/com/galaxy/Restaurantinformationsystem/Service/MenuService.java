package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;

public interface MenuService {

    public MenuDTO createMenuDTO(MenuDTO MenuDTO);

    public MenuDTO readMenuDTO(MenuDTO MenuDTO);

    public MenuDTO updateMenuDTO(MenuDTO MenuDTO);

    public void deleteMenu(MenuDTO MenuDTO);
}
