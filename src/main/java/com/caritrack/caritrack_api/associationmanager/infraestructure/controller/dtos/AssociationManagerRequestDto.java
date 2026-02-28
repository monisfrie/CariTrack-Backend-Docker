package com.caritrack.caritrack_api.associationmanager.infraestructure.controller.dtos;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationManagerRequestDto {

    private UUID userId;
    private Long associationId;
}
