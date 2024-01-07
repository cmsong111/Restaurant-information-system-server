package com.galaxy.Restaurantinformationsystem.config;

import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailServiceCustom implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new RuntimeException("가입되지 않은 유저입니다.");
        }

        return User.builder()
                .username(email)
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().toString())
                .build();

    }

}
