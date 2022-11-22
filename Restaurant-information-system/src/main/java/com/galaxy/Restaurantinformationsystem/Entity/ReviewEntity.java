package com.galaxy.Restaurantinformationsystem.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "spk")
    @ManyToOne
    private StoreEntity storeEntity;
    @JoinColumn(name = "upk")
    @ManyToOne
    private UserEntity userEntity;
}
