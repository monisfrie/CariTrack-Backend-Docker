package com.caritrack.caritrack_api.request.infraestructure.mapper;

import com.caritrack.caritrack_api.request.domain.Request;
import com.caritrack.caritrack_api.request.infraestructure.repository.RequestJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RequestRepositoryMapper {

    RequestJpa toJpa(Request request);

    Request toDomain(RequestJpa requestJpa);
}