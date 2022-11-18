package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class MenuController {

    MenuService MenuService;

    @Autowired
    public MenuController(MenuService MenuService) {
        this.MenuService = MenuService;
    }

    @PostMapping("/create")
    public MenuDTO createMenu(@RequestBody MenuDTO MenuDTO) {
        return MenuService.createMenuDTO(MenuDTO);
    }

    @PostMapping("/update")
    public MenuDTO updateMenu(@RequestBody MenuDTO MenuDTO) {
        return MenuService.updateMenuDTO(MenuDTO);
    }

    @PostMapping("/read")
    public MenuDTO readMenu(@RequestBody MenuDTO MenuDTO) {
        return null;
    }

    @DeleteMapping("/delete")
    public void deleteMenu(@RequestBody MenuDTO MenuDTO) {
        MenuService.deleteMenu(MenuDTO);
    }
}
