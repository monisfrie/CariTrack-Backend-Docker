package com.caritrack.caritrack_api.donation.infraestructure.controller.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationCreateRequestDto {

    @NotNull(message = "{error.blank.donation.userId}")
    private UUID userId;

    @NotNull(message = "{error.blank.donation.requestId}")
    private Long requestId;

    @NotNull(message = "{error.blank.donation.items}")
    private List<DonationItemLineDto> items;
}