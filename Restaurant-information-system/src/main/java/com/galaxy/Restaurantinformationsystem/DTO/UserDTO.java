package com.galaxy.Restaurantinformationsystem.DTO;


import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
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
    List<ReviewDTO> reviewDTO;

    public UserEntity toEntity() {

        List<StoreEntity>  storeEntityList = new ArrayList<>();
        if (storeDTO != null) {
            for (StoreDTO store : storeDTO) {
                storeEntityList.add(store.toEntity());
            }
        }
        List<ReviewEntity> ReviewEntities = new ArrayList<>();
        if (reviewDTO != null){
            for (ReviewDTO reviewDTO : reviewDTO) {
                ReviewEntities.add(reviewDTO.toEntity());
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
                .reviewEntity(ReviewEntities)
                .build();
    }
}
