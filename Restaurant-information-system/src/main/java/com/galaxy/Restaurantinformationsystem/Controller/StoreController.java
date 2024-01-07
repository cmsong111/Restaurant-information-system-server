package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Service.StoreService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Tag(name = "Store", description = "Store API")
@RestController
@AllArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private StoreService storeService;

    @PostMapping("/create")
    public StoreDTO createStore(@RequestBody StoreDTO storeDTO) {
        return storeService.createStoreDTO(storeDTO);
    }

    @PostMapping("/update")
    public StoreDTO updateUpdate(@RequestBody StoreDTO storeDTO) {
        if (storeDTO.getSPK() != null) {
            return storeService.updateStoreDTO(storeDTO);
        } else {
            return null;
        }
    }

    @GetMapping("/search-location-random-one")
    public StoreDTO serchBylocation(@RequestParam String location1, String location2) {
        return storeService.searchByLocationArray(location1, location2);
    }

    @GetMapping("/search-name")
    public ArrayList<StoreDTO> searchByName(@RequestParam String location1, String location2, String name) {
        ArrayList<StoreDTO> results = storeService.serchByNameAndLocation(location1, location2, name);
        return results;
    }

    @GetMapping("/search-category")
    public ArrayList<StoreDTO> searchByCategory(@RequestParam String category, String location1, String location2) {
        ArrayList<StoreDTO> results = storeService.serchByCategoryAndLocation2(category, location1, location2);
        return results;
    }

    @GetMapping("/search-id")
    public Optional<StoreDTO> searchBySPK(@RequestParam long id) {
        return storeService.serchById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteStore(
            @PathVariable("id") Long id,
            @RequestBody StoreDTO storeDTO) {
        if (storeDTO.getSPK() == null) {
            return "failed";
        } else {
            storeService.deleteStore(storeDTO);
            return "requested";
        }
    }

    @GetMapping("/searchByUPK")
    public List<StoreDTO> searchByUPK(@RequestParam long id) {
        return storeService.searchBySPK(id);
    }


    @PostMapping("/serch-overwall")
    public ArrayList<StoreDTO> serchOverwall(@RequestBody StoreDTO storeDTO) {
        return storeService.fineOverAll(storeDTO);
    }
}

