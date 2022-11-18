package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServicelmpl {

    MenuRepository MenuRepository;

    @Autowired
    public MenuServicelmpl(MenuRepository MenuRepository) {
        this.MenuRepository = MenuRepository;
    }

    @Override
    public MenuDTO createMenuDTO(@NotNull MenuDTO MenuDTO) {
        MenuEntity MenuEntity = MenuDTO.toEntity();
        return MenuRepository.save(MenuEntity).toDTO();
    }

    @Override
    public MenuDTO readMenuDTO(MenuDTO MenuDTO) {
        return null;
    }

    @Override
    public MenuDTO updateMenuDTO(MenuDTO MenuDTO) {
        MenuEntity MenuEntity = MenuDTO.toEntity();
        return MenuEntity.toDTO();
    }

    @Override
    public void deleteMenu(MenuDTO MenuDTO) {
        MenuEntity MenuEntity = MenuDTO.toEntity();
        MenuRepository.delete(MenuEntity);
    }
}
