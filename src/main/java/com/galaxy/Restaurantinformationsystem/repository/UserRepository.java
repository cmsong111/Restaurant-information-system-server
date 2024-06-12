package com.galaxy.Restaurantinformationsystem.repository;

import com.galaxy.Restaurantinformationsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {


    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);


}

