package com.caritrack.caritrack_api.donation.infraestructure.mapper;

import com.caritrack.caritrack_api.donation.domain.Donation;
import com.caritrack.caritrack_api.donation.infraestructure.repository.DonationJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DonationRepositoryMapper {

    DonationJpa toJpa(Donation donation);

    Donation toDomain(DonationJpa donationJpa);
}