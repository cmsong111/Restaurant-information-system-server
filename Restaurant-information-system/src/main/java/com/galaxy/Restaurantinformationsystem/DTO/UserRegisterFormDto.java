package com.galaxy.Restaurantinformationsystem.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterFormDto {
    @Schema(description = "이메일", example = "user@user.com")
    private String email;
    @Schema(description = "비밀번호", example = "1234")
    private String password;
    @Schema(description = "이름", example = "user")
    private String name;
}
