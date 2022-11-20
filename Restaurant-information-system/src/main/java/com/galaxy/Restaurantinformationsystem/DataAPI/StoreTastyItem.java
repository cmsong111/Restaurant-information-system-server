package com.galaxy.Restaurantinformationsystem.DataAPI;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StoreTastyItem {
    public int UC_SEQ;
    public String MAIN_TITLE;
    public String GUGUN_NM;
    public double LAT;
    public double LNG;
    public String PLACE;
    public String TITLE;
    public String SUBTITLE;
    public String ADDR1;
    public String ADDR2;
    public String CNTCT_TEL;
    public String HOMEPAGE_URL;
    public String USAGE_DAY_WEEK_AND_TIME;
    public String RPRSNTV_MENU;
    public String MAIN_IMG_NORMAL;
    public String MAIN_IMG_THUMB;
    public String ITEMCNTNTS;
}


