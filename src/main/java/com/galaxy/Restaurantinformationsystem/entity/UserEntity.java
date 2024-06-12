package com.galaxy.Restaurantinformationsystem.entity;

import com.galaxy.Restaurantinformationsystem.common.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)

    private String email;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
