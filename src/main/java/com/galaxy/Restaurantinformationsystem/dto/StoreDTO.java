package com.galaxy.Restaurantinformationsystem.dto;

import com.galaxy.Restaurantinformationsystem.common.FoodType;
import com.galaxy.Restaurantinformationsystem.entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StoreDTO {
    private Long id;

    private String name;
    private String callNumber;
    private String image;
    private String location;

    private FoodType category;

    private String businessHour;

    private Double latitude;
    private Double longitude;

    private UserEntity adminId;
}


