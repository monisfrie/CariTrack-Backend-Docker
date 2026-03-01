package com.caritrack.caritrack_api.itemstatistics.application.mapper;

import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatistics;
import com.caritrack.caritrack_api.itemstatistics.infraestructure.controller.dtos.ItemStatisticsResponseDto;
import com.caritrack.caritrack_api.itemstatistics.infraestructure.controller.dtos.ItemStatisticsUpsertDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemStatisticsMapperService {

    ItemStatistics toDomain(ItemStatisticsUpsertDto dto);

    ItemStatisticsResponseDto toResponseDto(ItemStatistics domain);
}