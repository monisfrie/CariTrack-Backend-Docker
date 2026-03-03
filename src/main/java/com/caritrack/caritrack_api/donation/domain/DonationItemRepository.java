package com.caritrack.caritrack_api.donation.domain;

import java.util.List;

public interface DonationItemRepository {

    DonationItem save(DonationItem donationItem);

    List<DonationItem> saveAll(List<DonationItem> donationItems);

    List<DonationItem> findByDonationId(Long donationId);

    void deleteByDonationId(Long donationId);
}