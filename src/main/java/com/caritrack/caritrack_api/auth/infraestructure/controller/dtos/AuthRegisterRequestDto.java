package com.caritrack.caritrack_api.auth.infraestructure.controller.dtos;

import com.caritrack.caritrack_api.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterRequestDto {

    @NotBlank private String username;
    @NotBlank private String name;

    @Email @NotBlank private String email;

    @NotBlank private String password;

    private Role role;
}