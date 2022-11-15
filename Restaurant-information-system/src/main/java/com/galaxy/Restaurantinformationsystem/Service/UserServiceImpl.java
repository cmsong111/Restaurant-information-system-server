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
    public UserDTO getUser(UserDTO user) {
        return userRepository.findById(user.getID()).orElse(null).toDTO();
    }

    @Override
    public UserDTO deleteUser(UserDTO user) {
        userRepository.delete(user.toEntity());
        return null;
    }

    @Override
    public boolean checkDuplicateID(String id) {
        return userRepository.existsById(id);
    }
}
