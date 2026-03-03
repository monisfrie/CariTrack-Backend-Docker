package com.caritrack.caritrack_api.itemstatistics.infraestructure.mapper;

import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatistics;
import com.caritrack.caritrack_api.itemstatistics.infraestructure.repository.ItemStatisticsJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemStatisticsRepositoryMapper {

    ItemStatisticsJpa toJpa(ItemStatistics stats);

    ItemStatistics toDomain(ItemStatisticsJpa statsJpa);
}