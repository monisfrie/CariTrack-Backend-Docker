package com.caritrack.caritrack_api.donation.infraestructure.repository;

import com.caritrack.caritrack_api.donation.domain.Donation;
import com.caritrack.caritrack_api.donation.domain.DonationRepository;
import com.caritrack.caritrack_api.donation.infraestructure.mapper.DonationRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DonationRepositoryImpl implements DonationRepository {

    private final DonationJpaRepository jpaRepository;
    private final DonationRepositoryMapper mapper;

    @Override
    public Donation save(Donation donation) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(donation)));
    }

    @Override
    public Optional<Donation> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Donation> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Donation> findByRequestId(Long requestId) {
        return jpaRepository.findByRequestId(requestId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Donation> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}