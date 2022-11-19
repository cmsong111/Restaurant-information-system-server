package com.galaxy.Restaurantinformationsystem.Entity;


import com.galaxy.Restaurantinformationsystem.DTO.ReviewDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rpk")
    private Long RPK;

    private String title;
    private String content;
    private String image;

    @JoinColumn(name = "menu")
    @ManyToOne
    private MenuEntity menuEntity;

    @JoinColumn(name = "upk")
    @ManyToOne
    private UserEntity userEntity;


    public ReviewDTO toDTO(){
        return ReviewDTO.builder()
                .RPK(RPK)
                .title(title)
                .content(content)
                .image(image)
                .userDTO(userEntity.toDTO())
                .menuDTO(menuEntity.toDTO())
                .build();
    }
}
