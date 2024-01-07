package com.galaxy.Restaurantinformationsystem.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RPK;
    private String title;
    private String content;
    private String image;
    @ManyToOne
    private StoreEntity storeEntity;
    @ManyToOne
    private UserEntity userEntity;
}
