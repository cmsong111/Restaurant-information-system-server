package com.galaxy.Restaurantinformationsystem.dto;

import com.galaxy.Restaurantinformationsystem.common.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInfoDto {
    private long id;
    private String email;
    private String password;
    private String name;
    private UserRole role;
}
