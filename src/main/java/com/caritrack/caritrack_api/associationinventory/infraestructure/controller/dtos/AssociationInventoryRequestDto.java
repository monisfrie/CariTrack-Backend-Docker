package com.caritrack.caritrack_api.associationinventory.infraestructure.controller.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationInventoryRequestDto {

    @NotNull(message = "{error.blank.inventory.associationId}")
    private Long associationId;

    @NotNull(message = "{error.blank.inventory.itemId}")
    private Long itemId;

    @NotNull(message = "{error.blank.inventory.stockQuantity}")
    @Min(value = 0, message = "{error.min.inventory.stockQuantity}")
    private Integer stockQuantity;

    @Min(value = 0, message = "{error.min.inventory.minRequired}")
    private Integer minRequired;

    @Min(value = 0, message = "{error.min.inventory.maxCapacity}")
    private Integer maxCapacity;
}