package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/store")
public class StoreController {

    StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public StoreDTO createStore(@RequestBody StoreDTO storeDTO) {
        return storeService.createStoreDTO(storeDTO);
    }

    @PostMapping("/update")
    public StoreDTO updateStore(@RequestBody StoreDTO storeDTO) {
        return storeService.updateStoreDTO(storeDTO);
    }

    @PostMapping("/read")
    public StoreDTO readStore(@RequestBody StoreDTO storeDTO) {
        return null;
    }

    @DeleteMapping("/delete")
    public void deleteStore(@RequestBody StoreDTO storeDTO) {
        storeService.deleteStore(storeDTO);
    }


}
