package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.DataAPI.HttpAPI;
import com.galaxy.Restaurantinformationsystem.DataAPI.StoreKidsPageDTO;
import com.galaxy.Restaurantinformationsystem.Service.StoreService;
import com.google.gson.Gson;
import lombok.Builder;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {

    StoreService storeService;
    Gson gson = new Gson();
    HttpAPI httpAPI;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public StoreController(StoreService storeService, HttpAPI httpAPI) {
        this.httpAPI = httpAPI;
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

    @PostMapping("/serch-location")
    public ArrayList<StoreDTO> serchBylocation(@RequestBody StoreDTO storeDTO) {
        ArrayList<StoreDTO> results = storeService.searchByLocationArray(storeDTO);
        return results;
    }

    @PostMapping("/serch-name")
    public StoreDTO serchByName(@RequestBody StoreDTO storeDTO) {
        return null;
    }

    @PostMapping("/serch-category")
    public StoreDTO serchByCategory(@RequestBody StoreDTO storeDTO) {
        return null;
    }

    @GetMapping("/serch-id")
    public Optional<StoreDTO> searchBySPK(@RequestParam long id) {
        return storeService.serchById(id);
    }


    @DeleteMapping("/delete")
    public void deleteStore(@RequestBody StoreDTO storeDTO) {
        storeService.deleteStore(storeDTO);
    }

    @ResponseBody
    @PostMapping("/update-kids")
    public String updateKids() throws IOException, URISyntaxException {

        // 전체 Data count 구하기
        int Totalcount = gson.fromJson(httpAPI.getKidsStore(1, 1), StoreKidsPageDTO.class).getTotalCount();
        int perPage = 10000;
        int forCount = (Totalcount / perPage) + 1;

        logger.info("Total Count is : " + Totalcount);
        logger.info("for Count is : " + forCount);


        // 전체 Data 저장하기
        for (int Page = 1; Page < forCount + 1; Page++) {

            Runnable d = updateKidsMutilthred.builder().storeService(storeService).page(Page).perPage(perPage).build();

            Thread thread = new Thread(d);

            thread.start();
        }
        return "Update Requested";
    }

    @ResponseBody
    @PostMapping("/update-tasty")
    public String updateTasty() throws IOException, URISyntaxException{
        int perPage = 300;
        int page = 1;
        storeService.updateTasty(perPage,page);
        return "update requested!";
    }
}

@Builder
class updateKidsMutilthred extends Thread {
    StoreService storeService;
    int page;
    int perPage;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @SneakyThrows
    @Override
    public void run() {
        storeService.updateKids(perPage, page);
        logger.info("Update Finished");
    }


}


