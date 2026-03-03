package com.caritrack.caritrack_api.user.infraestructure.mapper;

import com.caritrack.caritrack_api.user.domain.User;
import com.caritrack.caritrack_api.user.infraestructure.repository.UserJpa;
import org.mapstruct.*;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserRepositoryMapper {

    User toDomain (UserJpa userJpa);

    UserJpa toJpa (User user);

}
