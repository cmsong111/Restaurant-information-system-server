package com.galaxy.Restaurantinformationsystem.DataAPI;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StoreGoodDataDTO {
    public String 업소명;
    public String 업소구문;
    public String 도로명주소;
    public String 전화번호;
    public String 지역;
    public String 소개;
    public String 메뉴1;
    public String 메뉴2;
    public String 가격1;
    public String 가격2;
}
