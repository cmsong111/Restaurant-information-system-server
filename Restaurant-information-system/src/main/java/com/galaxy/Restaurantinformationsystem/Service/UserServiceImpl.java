package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO user) {
        UserEntity userEntity = user.toEntity();
        return userRepository.save(userEntity).toDTO();
    }

    @Override
    public UserDTO loginUser(String id, String password) {
        UserEntity userEntity = userRepository.readbylogin(id, password);
        System.out.println(userEntity.toString());
        return userEntity.toDTO();
    }

    @Override
    public UserDTO deleteUser(UserDTO user) {
        userRepository.delete(user.toEntity());
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        return userRepository.save(user.toEntity()).toDTO();
    }
}
