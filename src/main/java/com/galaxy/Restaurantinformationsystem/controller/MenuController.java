package com.galaxy.Restaurantinformationsystem.controller;

import com.galaxy.Restaurantinformationsystem.dto.MenuDTO;
import com.galaxy.Restaurantinformationsystem.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    MenuService menuService;

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

    @PostMapping("/delete")
    public String deleteMenu(@RequestBody MenuDTO MenuDTO) {
        menuService.deleteMenu(MenuDTO);
        return "delete Requested";
    }
}
