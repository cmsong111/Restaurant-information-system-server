package com.galaxy.Restaurantinformationsystem.DataAPI;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StoreKidDataDTO {
    public String 가맹점기준일자;
    public String 가맹점명칭;
    public Double 경도;
    public String 데이터작성일자;
    public String 도로명주소;
    public int 순번;
    public String 업종;
    public Double 위도;
    public String 전화번호;
    public String 지번주소;
}
