package com.galaxy.Restaurantinformationsystem.DataAPI;

import lombok.Data;

import java.util.ArrayList;


@Data
public class StoreKidsPageDTO {
    public int currentCount;
    public ArrayList<StoreKidDataDTO> data;
    public int matchCount;
    public int page;
    public int perPage;
    public int totalCount;
}

