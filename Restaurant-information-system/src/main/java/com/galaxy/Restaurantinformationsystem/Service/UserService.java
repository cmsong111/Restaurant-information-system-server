package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;

public interface UserService {
    public UserDTO createUser(UserDTO user);

    public UserDTO loginUser(String id, String password);

    public UserDTO updateUser(UserDTO user);

    public UserDTO deleteUser(UserDTO user);
}
