package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/menu")
public class MenuController {

    MenuService menuService;


    @Autowired
    public MenuController(MenuService MenuService) {
        this.menuService = MenuService;
    }

    @PostMapping("/create")
    public MenuDTO createMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.createMenuDTO(menuDTO);
    }

    @PostMapping("/update")
    public MenuDTO updateMenu(@RequestBody MenuDTO menuDTO) {
        return menuService.updateMenuDTO(menuDTO);
    }

    @GetMapping("/readInStore")
    public ArrayList<MenuDTO> read(@RequestParam Long spk) {
        return menuService.readMenuInStore(spk);
    }

    @ResponseBody
    @PostMapping("/delete")
    public String deleteMenu(@RequestBody MenuDTO MenuDTO) {
        menuService.deleteMenu(MenuDTO);
        return "delete Requested";
    }
}
