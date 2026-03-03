package com.caritrack.caritrack_api.item.application.mapper;

import com.caritrack.caritrack_api.item.domain.Item;
import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemRequestDto;
import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ItemMapperService {

    Item toDomain(ItemRequestDto requestDto);

    ItemResponseDto toResponseDto(Item item);
}