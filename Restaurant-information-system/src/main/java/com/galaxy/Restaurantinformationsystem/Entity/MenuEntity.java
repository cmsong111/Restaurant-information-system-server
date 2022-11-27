package com.galaxy.Restaurantinformationsystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "menu")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mpk")
    private Long MPK;
    private String name;
    private int price;
    private String image;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "spk")
    private StoreEntity store;
}
