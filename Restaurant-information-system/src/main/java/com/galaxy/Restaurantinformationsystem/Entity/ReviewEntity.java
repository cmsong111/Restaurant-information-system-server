package com.galaxy.Restaurantinformationsystem.Entity;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rpk")
    private Long RPK;
    private String title;
    private String content;
    private String image;
    @ManyToOne
    private StoreEntity storeEntity;
    @ManyToOne
    private UserEntity userEntity;
}
