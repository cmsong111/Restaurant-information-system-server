package com.galaxy.Restaurantinformationsystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "call_number")
    private String call;
    private String thumbnail;

    private String location1;
    private String location2;
    private String location3;
    private String category;
    @Column(name = "start_time")
    private Timestamp startTime;
    @Column(name = "end_time")
    private Timestamp endTime;
    @Column(name = "location_x")
    private Double locationX;
    @Column(name = "location_y")
    private Double locationY;
    private boolean kids;
    private boolean price;
    private boolean tasty;
    @Column(name = "role_model")
    private boolean roleModel;

    @ManyToOne
    private UserEntity adminUser;
}
