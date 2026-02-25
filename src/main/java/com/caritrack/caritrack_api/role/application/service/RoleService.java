package com.caritrack.caritrack_api.role.application.service;

import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleRequestDto;
import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleResponseDto;

import java.util.List;

public interface RoleService {

    RoleResponseDto createRole (RoleRequestDto roleRequestDto);

    List<RoleResponseDto> getAllRoles();

    RoleResponseDto getById(Long id);

    void deleteRole (Long id);

}
