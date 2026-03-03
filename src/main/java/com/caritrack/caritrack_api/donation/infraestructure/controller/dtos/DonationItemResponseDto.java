package com.caritrack.caritrack_api.donation.infraestructure.controller.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationItemResponseDto {
    private Long id;
    private Long itemId;
    private Integer quantity;
}