package com.galaxy.Restaurantinformationsystem.Controller;


import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import com.galaxy.Restaurantinformationsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        if (userDTO.getID() == null && userDTO.getPassword() == null) {
            return null;
        }
        return userService.loginUser(userDTO.getID(), userDTO.getPassword());
    }

    //회원 업데이트
    @PostMapping("/update")
    public UserDTO userUpdate(@RequestBody UserDTO userDTO) {
        userDTO = userService.updateUser(userDTO);

        return userDTO;
    }

    //삭제
    @DeleteMapping("/delete")
    public String userDelete(@RequestBody UserDTO user) {
        userService.deleteUser(user);
        return "Delete Done";
    }


    private String sha256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            return bytes.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
