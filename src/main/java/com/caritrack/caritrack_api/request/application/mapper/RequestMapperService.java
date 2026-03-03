package com.caritrack.caritrack_api.request.application.mapper;

import com.caritrack.caritrack_api.request.domain.Request;
import com.caritrack.caritrack_api.request.infraestructure.controller.dtos.RequestRequestDto;
import com.caritrack.caritrack_api.request.infraestructure.controller.dtos.RequestResponseDto;
import org.mapstruct.*;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RequestMapperService {

    Request toDomain(RequestRequestDto dto);

    RequestResponseDto toResponseDto(Request domain);
}