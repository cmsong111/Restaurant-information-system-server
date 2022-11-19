package com.galaxy.Restaurantinformationsystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "store")
public class StoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spk")
    private Long SPK;
    private String name;
    @Column(name = "call_number")
    private String call;
    private String location1;
    private String location2;
    private String location3;
    private String category;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "location_x")
    private double locationX;
    @Column(name = "location_y")
    private double locationY;
    private boolean kids;
    private boolean price;
    private boolean tasty;
    @Column(name = "role_model")
    private boolean roleModel;
    @OneToMany
    private List<ReviewEntity> reviews = new ArrayList<>();
    @OneToMany
    private List<MenuEntity> menus = new ArrayList<>();
    @ManyToOne()
    @JoinColumn(name = "upk")
    @Nullable
    private UserEntity adminUser;
}
