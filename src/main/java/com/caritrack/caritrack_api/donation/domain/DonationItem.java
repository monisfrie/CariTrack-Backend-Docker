package com.caritrack.caritrack_api.donation.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationItem {

    private Long id;
    private Long donationId;
    private Long itemId;
    private Integer quantity;
}