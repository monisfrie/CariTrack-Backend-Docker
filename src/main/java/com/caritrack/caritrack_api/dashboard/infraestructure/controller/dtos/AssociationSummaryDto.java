package com.caritrack.caritrack_api.dashboard.infraestructure.controller.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationSummaryDto {

    private Long associationId;

    private Integer totalDifferentItems; // cuántos items distintos tiene en inventario
    private Integer totalStock;          // suma de stockQuantity
}