package com.galaxy.Restaurantinformationsystem.Entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UPK;
    @NonNull
    @Column(name = "id", unique = true)
    private String ID;
    private String password;
    private String name;
    private int age;
    @Column(name = "id_amdin")
    private boolean isAdmin;
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @Nullable
    private List<StoreEntity> storeEntity = new ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @Nullable
    private List<ReviewEntity> reviewEntity = new ArrayList<>();
}
