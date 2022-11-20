package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/menu")
public class MenuController {

    com.galaxy.Restaurantinformationsystem.Service.MenuService MenuService;

    @Autowired
    public MenuController(MenuService MenuService) {
        this.MenuService = MenuService;
    }

    @PostMapping("/create")
    public MenuDTO createMenu(@RequestBody MenuDTO menuDTO) {
        return MenuService.createMenuDTO(menuDTO);
    }

    @PostMapping("/update")
    public MenuDTO updateMenu(@RequestBody MenuDTO menuDTO) {
        return MenuService.updateMenuDTO(menuDTO);
    }

    @PostMapping("/read")
    public MenuDTO readMenu(@RequestBody MenuDTO menuDTO) {
        return MenuService.readMenuDTO(menuDTO);
    }

    @ResponseBody
    @DeleteMapping("/delete")
    public String deleteMenu(@RequestBody MenuDTO MenuDTO) {
        MenuService.deleteMenu(MenuDTO);
        return "delete Requested";
    }
}
