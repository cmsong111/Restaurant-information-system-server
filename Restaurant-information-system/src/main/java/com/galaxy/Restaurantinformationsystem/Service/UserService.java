package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.UserInfoDto;
import com.galaxy.Restaurantinformationsystem.DTO.UserRegisterFormDto;
import com.galaxy.Restaurantinformationsystem.Entity.UserEntity;
import com.galaxy.Restaurantinformationsystem.Repository.ReviewRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import com.galaxy.Restaurantinformationsystem.common.UserRole;
import com.galaxy.Restaurantinformationsystem.config.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;
    StoreRepository storeRepository;
    ReviewRepository reviewRepository;
    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider jwtTokenProvider;


    public UserInfoDto registerUser(UserRegisterFormDto userRegisterFormDto) {
        if (userRepository.existsByEmail(userRegisterFormDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        userRegisterFormDto.setPassword(passwordEncoder.encode(userRegisterFormDto.getPassword()));
        UserEntity userEntity = modelMapper.map(userRegisterFormDto, UserEntity.class);
        userEntity.setRole(UserRole.USER);

        return modelMapper.map(userRepository.save(userEntity), UserInfoDto.class);
    }


    public String login(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new RuntimeException("가입되지 않은 유저입니다.");
        }
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return jwtTokenProvider.createToken(userEntity, userEntity.getRole());
    }

    public String deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다."));
        reviewRepository.deleteByUserEntity(userEntity);

        userRepository.delete(userEntity);
        return null;
    }

    public UserInfoDto updateUser(UserInfoDto userDTO, Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }

        if (userDTO.getId() != id) {
            throw new RuntimeException("본인의 정보만 수정할 수 있습니다.");
        }

        if (userDTO.getPassword() != null) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }


        return modelMapper.map(userRepository.save(modelMapper.map(userDTO, UserEntity.class)), UserInfoDto.class);
    }

    public Optional<UserInfoDto> searchById(Long id) {
        return userRepository.findById(id).map(userEntity -> modelMapper.map(userEntity, UserInfoDto.class));
    }

}
