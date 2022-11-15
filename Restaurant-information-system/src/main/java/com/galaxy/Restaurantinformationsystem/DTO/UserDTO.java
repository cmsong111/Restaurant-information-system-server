package com.galaxy.Restaurantinformationsystem.DTO;


import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {
    long UPK;
    String ID;
    String password;
    String name;
    int age;
    boolean isAdmin;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .UPK(UPK)
                .ID(ID)
                .name(name)
                .password(password)
                .age(age)
                .isAdmin(isAdmin)
                .build();
    }
}
