package com.caritrack.caritrack_api.category.infraestructure.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {

    @NotBlank(message = "{error.blank.category.name}")
    private String name;
}