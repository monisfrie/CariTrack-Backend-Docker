package com.caritrack.caritrack_api.donation.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationItemJpaRepository extends JpaRepository<DonationItemJpa, Long> {

    List<DonationItemJpa> findByDonationId(Long donationId);

    void deleteByDonationId(Long donationId);
}