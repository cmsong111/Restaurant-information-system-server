package com.galaxy.Restaurantinformationsystem.DTO;


import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDTO {
    private long UPK;
    private String ID;
    private String password;
    private String name;
    private int age;
    private boolean isAdmin;


    List<StoreDTO> storeDTO;

    public UserEntity toEntity() {

        List<StoreEntity>  storeEntityList = new ArrayList<>();
        if (storeDTO != null) {
            for (StoreDTO store : storeDTO) {
                storeEntityList.add(store.toEntity());
            }
        }

        return UserEntity.builder()
                .UPK(UPK)
                .ID(ID)
                .name(name)
                .password(password)
                .age(age)
                .isAdmin(isAdmin)
                .storeEntity(storeEntityList)
                .build();
    }
}
