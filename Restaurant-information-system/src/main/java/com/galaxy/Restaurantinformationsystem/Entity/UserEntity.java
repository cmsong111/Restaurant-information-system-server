package com.galaxy.Restaurantinformationsystem.Entity;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.DTO.ReviewDTO;
import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "upk", unique = true)   //속성명 수정
    @GeneratedValue(strategy = GenerationType.IDENTITY)//, generator = "USER_SEQ_GENERATOR")
    private  Long UPK;

    @NonNull
    @Column(name = "id", unique = true)
    private  String ID;
    private    String password;
    private  String name;
    private  int age;
    @Column(name = "id_amdin")
    private  boolean isAdmin;

    @OneToMany
    @JoinColumn(name = "upk")
    @Nullable
    private List<StoreEntity> storeEntity = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "rpk")
    @Nullable
    private List<ReviewEntity> reviewEntity = new ArrayList<>();


    public UserDTO toDTO() {

        List<StoreDTO> storeDTOArrayList = new ArrayList<>();
        if (storeEntity != null) {
            for (StoreEntity store : storeEntity) {
                storeDTOArrayList.add(store.toDTO());
            }
        }
        List<ReviewDTO> reviewDTOs = new ArrayList<>();
        if (reviewEntity != null) {
            for (ReviewEntity  review : reviewEntity) {
                reviewDTOs.add(review.toDTO());
            }
        }


        return UserDTO.builder()
                .UPK(UPK)
                .ID(ID)
                .password(password)
                .name(name)
                .age(age)
                .isAdmin(isAdmin)
                .storeDTO(storeDTOArrayList)
                .reviewDTO(reviewDTOs)
                .build();
    }
}
