package com.galaxy.Restaurantinformationsystem.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ReviewFormDTO {
    private String title;
    private String content;
    private String image;
    private int score;
}
