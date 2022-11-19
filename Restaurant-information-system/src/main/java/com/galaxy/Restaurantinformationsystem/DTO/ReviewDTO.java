package com.galaxy.Restaurantinformationsystem.DTO;

import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
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
    private String image;
    private MenuDTO menuDTO;
    private UserDTO userDTO;

    public ReviewEntity toEntity(){
        return ReviewEntity.builder()
                .RPK(RPK)
                .title(title)
                .content(content)
                .image(image)
                .menuEntity(menuDTO.toEntity())
                .userEntity(userDTO.toEntity())
                .build();
    }
}
