package com.caritrack.caritrack_api.auth.infraestructure.controller.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequestDto {

    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;
}