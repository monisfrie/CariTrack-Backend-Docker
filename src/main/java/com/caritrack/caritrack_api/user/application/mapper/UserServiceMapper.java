package com.caritrack.caritrack_api.user.application.mapper;

import com.caritrack.caritrack_api.auth.infraestructure.controller.dtos.AuthRegisterRequestDto;
import com.caritrack.caritrack_api.user.domain.User;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.*;
import org.mapstruct.*;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserServiceMapper {

    @Mapping(target = "passwordHash", source = "password")
    User requestToDomain(UserRequestDto dto);

    @Mapping(target = "id", source = "id")
    UserResponseDto toResponse(User domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "active", constant = "true")
    User registerToDomain(AuthRegisterRequestDto dto);

}
