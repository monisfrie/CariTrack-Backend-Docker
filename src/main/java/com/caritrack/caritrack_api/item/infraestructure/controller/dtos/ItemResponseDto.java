package com.caritrack.caritrack_api.item.infraestructure.controller.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;
    private String apiId;
    private String name;
    private String description;
    private String imageUrl;
    private String category;
}