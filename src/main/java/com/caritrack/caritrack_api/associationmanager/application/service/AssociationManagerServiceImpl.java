package com.caritrack.caritrack_api.associationmanager.application.service;

import com.caritrack.caritrack_api.association.domain.AssociationRepository;
import com.caritrack.caritrack_api.association.utils.exceptions.AssociationNotFoundException;
import com.caritrack.caritrack_api.associationmanager.application.mapper.AssociationManagerMapperService;
import com.caritrack.caritrack_api.associationmanager.domain.AssociationManagerRepository;
import com.caritrack.caritrack_api.associationmanager.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import com.caritrack.caritrack_api.user.domain.UserDao;
import com.caritrack.caritrack_api.user.utils.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssociationManagerServiceImpl
        implements AssociationManagerService {

    private final AssociationManagerRepository repository;
    private final AssociationManagerMapperService mapper;
    private final AssociationRepository associationRepository;
    private final UserDao userDao;
    private final MessageService messageService;

    @Override
    public AssociationManagerResponseDto create(AssociationManagerRequestDto request) {
        existingAssociation(request.getAssociationId());
        existingUser(request.getUserId());

        return mapper.toResponseDto(
                repository.save(mapper.toDomain(request))
        );
    }

    @Override
    public List<AssociationManagerResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public List<AssociationManagerResponseDto> getByAssociation(Long associationId) {
        existingAssociation(associationId);
        return repository.findByAssociationId(associationId)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public List<AssociationManagerResponseDto> getByUser(UUID userId) {
        existingUser(userId);
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    private void existingUser(UUID userId) {
        userDao.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(messageService.getMessage(
                        "error.not.found.user",
                        userId
                )));
    }

    private void existingAssociation(Long associationId) {
        associationRepository.findById(associationId)
                .orElseThrow(() -> new AssociationNotFoundException(messageService.getMessage(
                        "error.association.not.found",
                        associationId
                )));

    }
}
