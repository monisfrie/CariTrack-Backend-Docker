package com.caritrack.caritrack_api.dashboard.infraestructure.controller.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopReceivedItemDto {

    private Long itemId;
    private String itemName;
    private String imageUrl;

    private Integer totalReceived;
}