package com.caritrack.caritrack_api.item.infraestructure.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {

    @NotBlank(message = "{error.blank.item.apiId}")
    private String apiId;

    @NotBlank(message = "{error.blank.item.name}")
    private String name;

    private String description;

    private String imageUrl;

    private String category;
}