package com.galaxy.Restaurantinformationsystem.DataAPI;

import lombok.Data;

import java.util.ArrayList;

@Data
public class StoreGoodPageDTO {
    public int currentCount;
    public ArrayList<StoreGoodDataDTO> data;
    public int matchCount;
    public int page;
    public int perPage;
    public int totalCount;
}
