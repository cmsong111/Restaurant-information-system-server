package com.galaxy.Restaurantinformationsystem.entity;

import com.galaxy.Restaurantinformationsystem.common.FoodType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String callNumber;
    private String image;
    private String location;

    @Enumerated(EnumType.STRING)
    private FoodType category;

    private String businessHour;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private Timestamp createdAt;

    private Double latitude;
    private Double longitude;

    @ManyToOne
    private UserEntity admin;
}
