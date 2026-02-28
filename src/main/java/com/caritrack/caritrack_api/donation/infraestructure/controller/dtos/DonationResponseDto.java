package com.caritrack.caritrack_api.donation.infraestructure.controller.dtos;

import com.caritrack.caritrack_api.donation.domain.DonationStatus;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationResponseDto {

    private Long id;
    private UUID userId;
    private Long requestId;
    private Instant date;
    private DonationStatus status;

    private List<DonationItemResponseDto> items;
}