package com.caritrack.caritrack_api.user.infraestructure.controller.dtos;


import com.caritrack.caritrack_api.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "{error.username.blank}")
    private String username;
    @NotBlank(message = "{error.name.blank}")
    private String name;
    @NotBlank(message = "{error.name.blank}")
    @Email(message = "{error.enmail.invalid.format}")
    private String email;
    @NotBlank(message = "{error.password.blank}")
    private String password;
    @NotNull(message = "{error.role.null}")
    private Role role;
}

