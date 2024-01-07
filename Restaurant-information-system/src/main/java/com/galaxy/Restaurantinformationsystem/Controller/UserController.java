package com.galaxy.Restaurantinformationsystem.Controller;


import com.galaxy.Restaurantinformationsystem.DTO.UserInfoDto;
import com.galaxy.Restaurantinformationsystem.DTO.UserRegisterFormDto;
import com.galaxy.Restaurantinformationsystem.Service.UserService;
import com.galaxy.Restaurantinformationsystem.config.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입")
    public UserInfoDto registerUser(@RequestBody UserRegisterFormDto userDTO) {
        return userService.registerUser(userDTO);
    }

    // 회원 정보 읽기
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인")
    public ResponseEntity<String> userLogin(@Parameter(description = "이메일") @RequestParam(value = "email", required = true) String email, @Parameter(description = "비밀번호") @RequestParam(value = "password", required = true) String password) {
        String token = userService.login(email, password);
        return ResponseEntity.ok(token);
    }


    @PostMapping("/update")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정")
    public UserInfoDto userUpdate(@RequestBody UserInfoDto userDTO, HttpServletRequest req) {
        userDTO = userService.updateUser(userDTO, jwtTokenProvider.getUid(req));

        return userDTO;
    }


    @PostMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    public String userDelete(HttpServletRequest req) {
        userService.deleteUser(jwtTokenProvider.getUid(req));
        return "Delete Done";
    }

    @GetMapping("/whoami")
    @Operation(summary = "회원 정보 읽기", description = "회원 정보 읽기")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserInfoDto> whoami(HttpServletRequest req) {
        Optional<UserInfoDto> userDTO = userService.searchById(jwtTokenProvider.getUid(req));
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

