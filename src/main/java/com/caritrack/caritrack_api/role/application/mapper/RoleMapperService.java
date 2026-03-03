package com.caritrack.caritrack_api.role.application.mapper;

import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleRequestDto;
import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleResponseDto;
import com.caritrack.caritrack_api.role.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapperService {

    RoleResponseDto toResponseDto (Role role);

    Role toDomain (RoleRequestDto requestDto);

}
