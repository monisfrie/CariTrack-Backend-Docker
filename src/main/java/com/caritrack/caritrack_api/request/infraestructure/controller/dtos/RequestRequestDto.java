package com.caritrack.caritrack_api.request.infraestructure.controller.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRequestDto {

    @NotNull(message = "{error.blank.request.associationId}")
    private Long associationId;

    @NotBlank(message = "{error.blank.request.title}")
    private String title;

    private String summary;
    private String description;
}