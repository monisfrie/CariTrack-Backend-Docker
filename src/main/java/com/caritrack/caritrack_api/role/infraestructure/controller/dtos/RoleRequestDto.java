package com.caritrack.caritrack_api.role.infraestructure.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequestDto {

    @NotBlank(message = "{error.blank.role.name}")
    private String name;
}
