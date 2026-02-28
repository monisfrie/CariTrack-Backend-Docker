package com.caritrack.caritrack_api.association.application.service;

import com.caritrack.caritrack_api.association.application.mapper.AssociationMapperService;
import com.caritrack.caritrack_api.association.domain.Association;
import com.caritrack.caritrack_api.association.domain.AssociationRepository;
import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationRequestDto;
import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationResponseDto;
import com.caritrack.caritrack_api.association.utils.exceptions.AssociationNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssociationServiceImpl implements AssociationService {

    private final AssociationRepository repository;
    private final AssociationMapperService mapper;
    private final MessageService messageService;

    @Override
    public AssociationResponseDto createAssociation(AssociationRequestDto request) {

        Association association = mapper.toDomain(request);

        Association saved = repository.save(association);

        return mapper.toResponseDto(saved);
    }

    @Override
    public List<AssociationResponseDto> getAllAssociations() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssociationResponseDto getById(Long id) {
        Association association = repository.findById(id)
                .orElseThrow(() ->
                        new AssociationNotFoundException(
                                messageService.getMessage(
                                        "error.association.not.found",
                                        id
                                )
                        )
                );
        return mapper.toResponseDto(association);
    }

    @Override
    @Transactional
    public void deleteAssociation(Long id) {
        Association association = existingAssociation(id);
        repository.deleteById(association.getId());
    }



    @Override
    public AssociationResponseDto updateAssociation(Long id, AssociationRequestDto request) {

        Association existing = repository.findById(id)
                .orElseThrow(() ->
                        new AssociationNotFoundException(
                                messageService.getMessage(
                                        "error.association.not.found",
                                        id
                                )
                        )
                );

        existing.setName(request.getName());
        existing.setLocation(request.getLocation());
        existing.setDescription(request.getDescription());
        existing.setCategory(request.getCategory());

        Association updated = repository.save(existing);

        return mapper.toResponseDto(updated);
    }

    @Override
    public List<AssociationResponseDto> getByCategory(String category) {
        return repository.findByCategory(category)
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.findById(id).isPresent();
    }


    private Association existingAssociation(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AssociationNotFoundException(
                        messageService.getMessage("error.association.not.found", id)
                ));
    }
}

