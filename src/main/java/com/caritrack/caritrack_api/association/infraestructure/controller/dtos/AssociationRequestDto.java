package com.caritrack.caritrack_api.association.infraestructure.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationRequestDto {

    @NotBlank(message = "{error.blank.association.name}")
    private String name;

    private String location;

    private String description;

    private String category;
}

