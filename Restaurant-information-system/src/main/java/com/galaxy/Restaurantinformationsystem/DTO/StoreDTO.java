package com.galaxy.Restaurantinformationsystem.DTO;


import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private String category;
    @DateTimeFormat(pattern = "hh:mm:ss")
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean kids;
    private boolean price;
    private boolean tasty;

    private boolean roleModel;
    private double locationX;
    private double locationY;


    private List<ReviewDTO> reviews = new ArrayList<>();
    private List<MenuDTO> menus = new ArrayList<>();
    private UserDTO adminUser;


    public StoreEntity toEntity() {
        List<ReviewEntity> reviewEntities = new ArrayList<>();
        if (reviews != null) {
            for (ReviewDTO review : reviews) {
                reviewEntities.add(review.toEntity());
            }
        }
        List<MenuEntity> menuEntities = new ArrayList<>();
        if (reviews != null) {

            for (MenuDTO menu : menus) {
                menuEntities.add(menu.toEntity());
            }
        }

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
                .category(category)
                .tasty(tasty)
                .roleModel(roleModel)
                .locationX(locationX)
                .locationY(locationY)
                .adminUser((adminUser != null) ? adminUser.toEntity() : null)
                .reviews(reviewEntities)
                .menus(menuEntities)
                .build();

    }
}


