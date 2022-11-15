package com.galaxy.Restaurantinformationsystem.Entity;

import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "upk", unique = true)   //속성명 수정
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_SEQ_GENERATOR")
    Long UPK;
    @Id
    @Column(name = "id", unique = true)
    String ID;
    String password;
    String name;
    int age;
    @Column(name = "id_amdin")
    boolean isAdmin;


    public UserDTO toDTO() {
        return UserDTO.builder()
                .UPK(UPK)
                .ID(ID)
                .password(password)
                .name(name)
                .age(age)
                .isAdmin(isAdmin)
                .build();
    }
}
