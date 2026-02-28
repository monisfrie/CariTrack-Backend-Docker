package com.caritrack.caritrack_api.donation.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DonationJpaRepository extends JpaRepository<DonationJpa, Long> {

    List<DonationJpa> findByRequestId(Long requestId);

    List<DonationJpa> findByUserId(UUID userId);
}