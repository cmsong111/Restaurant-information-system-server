package com.galaxy.Restaurantinformationsystem.Entity;

import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import lombok.*;
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
@SequenceGenerator(
        name = "STORE_SEQ_GENERATOR",
        sequenceName = "STORE_SEQ")
@Table(name = "store")
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//, generator = "STORE_SEQ_GENERATOR")
    @Column(name = "spk")
    private Long SPK;
    private String name;
    @Column(name = "call_number")
    private String call;
    private String location1;
    private String location2;
    private String location3;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;

    private boolean kids;
    private boolean price;
    private boolean tasty;
    @Column(name = "role_model")
    private boolean rolemodel;

    @ManyToOne
    @JoinColumn(name = "upk",insertable = false, updatable = false)
    private UserEntity adminUser;
//    @OneToMany
//    private String menu;

    public StoreDTO toDTO() {
        return StoreDTO.builder()
                .SPK(SPK)
                .name(name)
                .call(call)
                .location1(location1)
                .location2(location2)
                .location3(location3)
                .startTime(startTime)
                .endTime(endTime)
                .kids(kids)
                .price(price)
                .tasty(tasty)
                .rolemodel(rolemodel)
                .adminUser(adminUser.toDTO())
                .build();
    }

}
