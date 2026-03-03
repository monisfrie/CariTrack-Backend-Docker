package com.caritrack.caritrack_api.donation.infraestructure.mapper;

import com.caritrack.caritrack_api.donation.domain.DonationItem;
import com.caritrack.caritrack_api.donation.infraestructure.repository.DonationItemJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DonationItemRepositoryMapper {

    DonationItemJpa toJpa(DonationItem donationItem);

    DonationItem toDomain(DonationItemJpa donationItemJpa);
}