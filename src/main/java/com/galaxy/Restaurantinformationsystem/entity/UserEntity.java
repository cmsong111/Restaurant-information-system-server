package com.galaxy.Restaurantinformationsystem.entity;

import com.galaxy.Restaurantinformationsystem.common.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> role;
}
