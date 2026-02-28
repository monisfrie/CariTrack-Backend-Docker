package com.caritrack.caritrack_api.donation.infraestructure.controller.dtos;

import com.caritrack.caritrack_api.donation.domain.DonationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationUpdateStatusDto {

    @NotNull(message = "{error.blank.donation.status}")
    private DonationStatus status;
}