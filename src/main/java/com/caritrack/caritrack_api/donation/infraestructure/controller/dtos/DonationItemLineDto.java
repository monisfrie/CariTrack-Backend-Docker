package com.caritrack.caritrack_api.donation.infraestructure.controller.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationItemLineDto {

    @NotNull(message = "{error.blank.donation.itemId}")
    private Long itemId;

    @NotNull(message = "{error.blank.donation.quantity}")
    @Min(value = 1, message = "{error.min.donation.quantity}")
    private Integer quantity;
}