package com.galaxy.Restaurantinformationsystem.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReviewDTO {
    private Long id;
    private String title;
    private String content;
    private String image;
    private int score;
    private Long storeEntityId;
    private Long userEntityId;
}
