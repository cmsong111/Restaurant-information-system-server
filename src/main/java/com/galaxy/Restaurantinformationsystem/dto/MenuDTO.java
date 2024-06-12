package com.galaxy.Restaurantinformationsystem.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuDTO {
    private Long MPK;
    private String name;
    private Long price;
    private String image;
    private Long SPK;
}
