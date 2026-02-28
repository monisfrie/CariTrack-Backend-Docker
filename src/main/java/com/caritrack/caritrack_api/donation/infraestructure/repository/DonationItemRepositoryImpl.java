package com.caritrack.caritrack_api.donation.infraestructure.repository;

import com.caritrack.caritrack_api.donation.domain.DonationItem;
import com.caritrack.caritrack_api.donation.domain.DonationItemRepository;
import com.caritrack.caritrack_api.donation.infraestructure.mapper.DonationItemRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DonationItemRepositoryImpl implements DonationItemRepository {

    private final DonationItemJpaRepository jpaRepository;
    private final DonationItemRepositoryMapper mapper;

    @Override
    public DonationItem save(DonationItem donationItem) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(donationItem)));
    }

    @Override
    public List<DonationItem> saveAll(List<DonationItem> donationItems) {
        var saved = jpaRepository.saveAll(donationItems.stream().map(mapper::toJpa).toList());
        return saved.stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<DonationItem> findByDonationId(Long donationId) {
        return jpaRepository.findByDonationId(donationId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteByDonationId(Long donationId) {
        jpaRepository.deleteByDonationId(donationId);
    }
}