package com.caritrack.caritrack_api.donation.application.mapper;

import com.caritrack.caritrack_api.donation.domain.Donation;
import com.caritrack.caritrack_api.donation.infraestructure.controller.dtos.DonationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DonationMapperService {

    DonationResponseDto toResponseDto(Donation donation);
}