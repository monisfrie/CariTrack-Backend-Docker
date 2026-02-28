package com.caritrack.caritrack_api.donation.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DonationRepository {

    Donation save(Donation donation);

    Optional<Donation> findById(Long id);

    List<Donation> findAll();

    List<Donation> findByRequestId(Long requestId);

    List<Donation> findByUserId(UUID userId);

    void deleteById(Long id);
}