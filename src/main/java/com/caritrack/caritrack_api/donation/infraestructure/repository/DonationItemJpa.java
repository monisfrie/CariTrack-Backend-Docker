package com.caritrack.caritrack_api.donation.infraestructure.repository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "donation_items")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationItemJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "donation_id", nullable = false)
    private Long donationId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Integer quantity;
}