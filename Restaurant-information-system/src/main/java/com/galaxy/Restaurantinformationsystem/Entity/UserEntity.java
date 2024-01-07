package com.galaxy.Restaurantinformationsystem.Entity;

import com.galaxy.Restaurantinformationsystem.common.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
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
