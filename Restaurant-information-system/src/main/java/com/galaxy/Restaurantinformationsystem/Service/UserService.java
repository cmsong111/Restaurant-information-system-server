package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;

public interface UserService {
    public UserDTO createUser(UserDTO user);
    public UserDTO getUser(String id, String password);
    public boolean checkDuplicateID(String id);
    public UserDTO updateUser(UserDTO user);

    public UserDTO deleteUser(UserDTO user);
}
