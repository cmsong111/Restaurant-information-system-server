package com.galaxy.Restaurantinformationsystem.Entity;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import lombok.*;
import org.apache.catalina.Store;

import javax.persistence.*;

@Entity
@Getter
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

    @ManyToOne
    @JoinColumn(name = "spk")
    private StoreEntity store;


    public MenuDTO toDTO() {
        return MenuDTO.builder()
                .MPK(MPK)
                .name(name)
                .price(price)
                .image(image)
                .build();
    }
}
