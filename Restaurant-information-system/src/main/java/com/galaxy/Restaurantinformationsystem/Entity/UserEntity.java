package com.galaxy.Restaurantinformationsystem.Entity;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
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
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ",
        allocationSize = 1,
        initialValue = 1)
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
    @Column(name = "id_amdin", columnDefinition = "VARCHAR(255) default 'false'")
    private  boolean isAdmin;

    @OneToOne
    @JoinColumn(name = "upk")
    @Nullable
    private List<StoreEntity> storeEntity;


    public UserDTO toDTO() {

        List<StoreDTO> storeDTOArrayList = new ArrayList<>();
        if (storeEntity != null) {
            for (StoreEntity store : storeEntity) {
                storeDTOArrayList.add(store.toDTO());
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
                .build();
    }
}
