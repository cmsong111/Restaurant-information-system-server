package com.galaxy.Restaurantinformationsystem.DTO;


import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StoreDTO {
    private Long SPK;
    private String name;
    private String call;
    private String location1;
    private String location2;
    private String location3;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean kids;
    private boolean price;
    private boolean tasty;

    private boolean rolemodel;

    private UserDTO adminUser;

    public StoreEntity toEntity() {
        return StoreEntity.builder()
                .SPK(SPK)
                .name(name)
                .call(call)
                .location1(location1)
                .location2(location2)
                .location3(location3)
                .startTime(startTime)
                .endTime(endTime)
                .kids(kids)
                .price(price)
                .tasty(tasty)
                .rolemodel(rolemodel)
                .adminUser(adminUser.toEntity())
                .build();

    }
}


