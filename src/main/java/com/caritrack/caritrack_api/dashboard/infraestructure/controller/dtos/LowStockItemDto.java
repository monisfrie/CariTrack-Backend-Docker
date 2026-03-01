package com.caritrack.caritrack_api.dashboard.infraestructure.controller.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LowStockItemDto {

    private Long itemId;
    private String itemName;
    private String imageUrl;

    private Integer stockQuantity;
    private Integer minRequired;
}