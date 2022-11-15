package com.galaxy.Restaurantinformationsystem.Controller;


import com.galaxy.Restaurantinformationsystem.DTO.UserDTO;
import com.galaxy.Restaurantinformationsystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
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
        // TODO : User 비밀번호 암호와
        // TODO : User 아이디 중복

        return userService.createUser(userDTO);
    }

    // 회원 정보 읽기
    @PostMapping("/read")
    public UserDTO userRead(@RequestBody UserDTO user) {
        // TODO : User 읽기 메소드 작성
        if (user.getID() == null && user.getPassword() == null) {
            return null;
        }
        return userService.getUser(user.getID(), user.getPassword());
    }

    //회원 업데이트
    @PostMapping("/update")
    public UserDTO userUpdate(@RequestBody UserDTO user) {
        // TODO : User 업데이트 메소드 작성

        userService.updateUser(user);

        return user;
    }

    //삭제
    @DeleteMapping("/delete")
    public UserDTO userDelete(@RequestBody UserDTO user){
        // TODO : 유저 제거 메소드 작성
        return userService.deleteUser(user);
    }

    // ID 중복체크 기능
    @PostMapping("/id-duplicate-check")
    public boolean userIdDuplicateCheck(@RequestBody UserDTO userDTO) {
        return userService.checkDuplicateID(userDTO.getID());
    }


}
