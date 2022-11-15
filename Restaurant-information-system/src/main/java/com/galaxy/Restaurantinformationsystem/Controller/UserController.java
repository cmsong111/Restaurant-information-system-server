package com.galaxy.Restaurantinformationsystem.Controller;


import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import com.galaxy.Restaurantinformationsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

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
    public UserDTO userCreate(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {

        // TODO : User 비밀번호 암호와
        // TODO : User 아이디 중복

        return userService.createUser(userDTO);
    }

    // 회원 정보 읽기
    @PostMapping("/read")
    public UserDTO userRead(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        userDTO.setPassword(sha256(userDTO.getPassword()));
        // TODO : User 읽기 메소드 작성
        if (userDTO.getID() == null && userDTO.getPassword() == null) {
            return null;
        }
        return userService.getUser(userDTO.getID(), userDTO.getPassword());
    }

    //회원 업데이트
    @PostMapping("/update")
    public UserDTO userUpdate(@RequestBody UserDTO userDTO) throws NoSuchAlgorithmException {
        userDTO = userService.updateUser(userDTO);

        return userDTO;
    }

    //삭제
    @DeleteMapping("/delete")
    public UserDTO userDelete(@RequestBody UserDTO user) {
        return userService.deleteUser(user);
    }

    // ID 중복체크 기능
    @PostMapping("/id-duplicate-check")
    public boolean userIdDuplicateCheck(@RequestBody UserDTO userDTO) {
        return userService.checkDuplicateID(userDTO.getID());
    }

    private String sha256(String password) throws NoSuchAlgorithmException {
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
