package com.caritrack.caritrack_api.role.infraestructure.mapper;

import com.caritrack.caritrack_api.role.domain.Role;
import com.caritrack.caritrack_api.role.infraestructure.repository.RoleJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleRepositoryMapper {

    RoleJpa toJpa (Role role);

    Role toDomain (RoleJpa roleJpa);

}
