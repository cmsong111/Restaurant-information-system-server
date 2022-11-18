package com.galaxy.Restaurantinformationsystem.Entity;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(name = "MENU_SEQ_GENERATOR",
        sequenceName = "MENU_SEQ")
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mpk")
    private Long MPK;

    private String name;
    private Boolean price;
    private Boolean image;

    @OneToMany
    @JoinColumn(name = "spk", insertable = false, updatable = false)


    public MenuDTO toDTO() {
        return MenuDTO.builder()
                .MPK(MPK)
                .name(name)
                .price(price)
                .image(image)
                .build();
    }
}
