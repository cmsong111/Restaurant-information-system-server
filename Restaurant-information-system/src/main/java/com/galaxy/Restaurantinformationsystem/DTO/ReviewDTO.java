package com.galaxy.Restaurantinformationsystem.DTO;

import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ReviewDTO {

    private Long RPK;

    private String title;
    private String content;
    private Boolean image;

    public ReviewEntity toEntity(){
        return ReviewEntity.builder()
                .RPK(RPK)
                .title(title)
                .content(content)
                .image(image)
                .build();
    }
}
