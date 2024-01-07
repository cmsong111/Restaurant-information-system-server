package com.galaxy.Restaurantinformationsystem.Controller;


import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import com.galaxy.Restaurantinformationsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/create")
    public UserDTO userCreate(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // 회원 정보 읽기
    @PostMapping("/login")
    public UserDTO userLogin(@RequestBody UserDTO userDTO) {
        if (userDTO.getEmail() == null && userDTO.getPassword() == null) {
            return null;
        }
        return userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
    }

    //회원 업데이트
    @PostMapping("/update")
    public UserDTO userUpdate(@RequestBody UserDTO userDTO) {
        userDTO = userService.updateUser(userDTO);

        return userDTO;
    }

    //삭제
    @PostMapping("/delete")
    public String userDelete(@RequestBody UserDTO user) {
        userService.deleteUser(user);
        return "Delete Done";
    }

}
