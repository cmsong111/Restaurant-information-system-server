package com.galaxy.Restaurantinformationsystem.DataAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class StoreTastyResult{
    public StoreTastyHeader header;
    public ArrayList<StoreTastyItem> item;
    public int numOfRows;
    public int pageNo;
    public int totalCount;
}

