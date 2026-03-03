package com.caritrack.caritrack_api.association.infraestructure.controller.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationResponseDto {

    private Long id;
    private String name;
    private String location;
    private String description;
    private String category;
}
