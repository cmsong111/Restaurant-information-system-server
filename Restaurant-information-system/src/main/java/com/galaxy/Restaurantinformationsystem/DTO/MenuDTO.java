package com.galaxy.Restaurantinformationsystem.DTO;

import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MenuDTO {

    private Long MPK;
    private String name;
    private int price;
    private String image;

    public MenuEntity toEntity(){
        return MenuEntity.builder()
                .MPK(MPK)
                .name(name)
                .price(price)
                .image(image)
                .build();
    }
}
