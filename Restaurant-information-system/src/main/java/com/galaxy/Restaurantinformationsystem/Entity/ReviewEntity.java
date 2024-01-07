package com.galaxy.Restaurantinformationsystem.Entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String image;
    private int score;
    @ManyToOne
    private StoreEntity storeEntity;
    @ManyToOne
    private UserEntity userEntity;
}
