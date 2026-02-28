package com.caritrack.caritrack_api.associationmanager.domain;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociationManager {

    private Long id;
    private UUID userId;
    private Long associationId;
}