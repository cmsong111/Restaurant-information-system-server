package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;

public interface UserService {
    public UserDTO createUser(UserDTO user);
    public UserDTO getUser(UserDTO user);
    public boolean checkDuplicateID(String id);

    public UserDTO deleteUser(UserDTO user);
}
