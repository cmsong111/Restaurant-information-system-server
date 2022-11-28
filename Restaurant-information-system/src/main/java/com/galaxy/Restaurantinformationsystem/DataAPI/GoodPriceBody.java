package com.galaxy.Restaurantinformationsystem.DataAPI;

import lombok.Data;

@Data

public class GoodPriceBody{
    public GoodPriceItems items;
    public int numOfRows;
    public int pageNo;
    public int totalCount;
}
