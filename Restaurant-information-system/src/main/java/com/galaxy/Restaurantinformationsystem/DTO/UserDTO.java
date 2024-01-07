package com.galaxy.Restaurantinformationsystem.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {
    private long id;
    private String email;
    private String password;
    private String name;
    private int age;
    private boolean isAdmin;
}
