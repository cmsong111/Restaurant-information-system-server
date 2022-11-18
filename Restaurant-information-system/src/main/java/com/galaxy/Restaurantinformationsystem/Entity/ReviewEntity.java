package com.galaxy.Restaurantinformationsystem.Entity;


import com.galaxy.Restaurantinformationsystem.DTO.ReviewDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "REVIEW_SEQ_GENERATOR",
        sequenceName = "REVIEW_SEQ")
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rpk")
    private Long RPK;

    private String title;
    private String content;
    private Boolean image;

    public ReviewDTO toDTO(){
        return ReviewDTO.builder()
                .RPK(RPK)
                .title(title)
                .content(content)
                .image(image)
                .build();
    }
}
