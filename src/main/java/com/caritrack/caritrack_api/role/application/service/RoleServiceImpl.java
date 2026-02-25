package com.caritrack.caritrack_api.role.application.service;

import com.caritrack.caritrack_api.role.application.mapper.RoleMapperService;
import com.caritrack.caritrack_api.role.domain.Role;
import com.caritrack.caritrack_api.role.domain.RoleRepository;
import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleRequestDto;
import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleResponseDto;
import com.caritrack.caritrack_api.role.utils.exceptions.RoleNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements  RoleService{

    private final RoleRepository roleRepository;
    private final RoleMapperService mapper;
    private final MessageService messageService;

    @Override
    public RoleResponseDto createRole(RoleRequestDto request) {

        Role role = Role.builder()
                .name(request.getName())
                .build();

        Role saved = roleRepository.save(role);

        return mapper.toResponseDto(saved);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto getById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new RoleNotFoundException(messageService.getMessage(
                        "error.role.not.found",
                        id
                ))
        );
        return mapper.toResponseDto(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
