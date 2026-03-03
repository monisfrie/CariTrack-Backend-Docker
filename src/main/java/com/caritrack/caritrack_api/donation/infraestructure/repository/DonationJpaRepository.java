package com.caritrack.caritrack_api.donation.infraestructure.repository;

import com.caritrack.caritrack_api.donation.domain.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DonationJpaRepository extends JpaRepository<DonationJpa, Long> {

    List<DonationJpa> findByRequestId(Long requestId);

    List<DonationJpa> findByUserId(UUID userId);

    @Query("""
        select d from DonationJpa d
        join RequestJpa r on r.id = d.requestId
        where r.associationId = :associationId
    """)
        List<DonationJpa> findByAssociation(Long associationId);

    @Query("""
        select d from DonationJpa d
        join RequestJpa r on r.id = d.requestId
        where r.associationId = :associationId and d.status = :status
    """)
        List<DonationJpa> findByAssociationAndStatus(Long associationId, DonationStatus status);

}