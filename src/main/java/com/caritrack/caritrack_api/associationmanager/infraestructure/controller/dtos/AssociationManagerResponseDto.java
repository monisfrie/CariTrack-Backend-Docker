package com.caritrack.caritrack_api.associationmanager.infraestructure.controller.dtos;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationManagerResponseDto {

    private Long id;
    private UUID userId;
    private Long associationId;
}
