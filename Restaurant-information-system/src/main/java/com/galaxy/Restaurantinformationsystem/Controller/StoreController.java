package com.galaxy.Restaurantinformationsystem.Controller;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.DataAPI.*;
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
import java.util.List;
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
    public StoreDTO updateUpdate(@RequestBody StoreDTO storeDTO) {
        if (storeDTO.getSPK() != null) {
            return storeService.updateStoreDTO(storeDTO);
        } else {
            return null;
        }
    }

    @GetMapping("/search-location-random-one")
    public StoreDTO serchBylocation(@RequestParam String location1, String location2) {
        //StoreDTO storeDTO = StoreDTO.builder().location1(location1).location2(location2).build();
        StoreDTO results = storeService.searchByLocationArray(location1, location2);
        return results;
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


    @ResponseBody
    @PostMapping("/delete")
    public String deleteStore(@RequestBody StoreDTO storeDTO) {
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
    public String updateTasty() throws IOException, URISyntaxException {
        int perPage = 300;
        int page = 1;
        storeService.updateTasty(perPage, page);
        return "update requested!";
    }

    @ResponseBody
    @PostMapping("/update-price")
    public String updatePrice() throws IOException, URISyntaxException {

        String result = httpAPI.getGoodPriceStore(1,1);
        // Data count 구하기
        int Totalcount = gson.fromJson(result, GoodPriceRoot.class).getGoodPriceStore.body.getTotalCount();
        int perPage = 500;
        int forCount = (Totalcount / perPage) + 1;

        logger.info("Total Count is : " + Totalcount);
        logger.info("for Count is : " + forCount);

        for (int Page = 1; Page < forCount + 1; Page++) {
            storeService.updateGoodPrice(perPage, Page);
        }

        return "update requested";
    }

    @ResponseBody
    @PostMapping("/serch-overwall")
    public ArrayList<StoreDTO> serchOverwall(@RequestBody StoreDTO storeDTO) {
        return storeService.fineOverAll(storeDTO);
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


