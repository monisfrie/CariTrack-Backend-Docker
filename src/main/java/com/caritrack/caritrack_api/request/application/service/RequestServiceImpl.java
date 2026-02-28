package com.caritrack.caritrack_api.request.application.service;

import com.caritrack.caritrack_api.association.domain.AssociationRepository;
import com.caritrack.caritrack_api.request.application.mapper.RequestMapperService;
import com.caritrack.caritrack_api.request.domain.Request;
import com.caritrack.caritrack_api.request.domain.RequestRepository;
import com.caritrack.caritrack_api.request.domain.RequestStatus;
import com.caritrack.caritrack_api.request.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.request.utils.exceptions.RequestNotFoundException;
import com.caritrack.caritrack_api.association.utils.exceptions.AssociationNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private final AssociationRepository associationRepository;
    private final RequestMapperService mapper;
    private final MessageService messageService;

    private final static String NOT_FOUND_REQUEST = "error.request.not.found";

    @Override
    public RequestResponseDto create(RequestRequestDto request) {

        existingAssociation(request.getAssociationId());

        Request domain = mapper.toDomain(request);
        domain.setStatus(RequestStatus.OPEN);

        return mapper.toResponseDto(repository.save(domain));
    }

    @Override
    public List<RequestResponseDto> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public RequestResponseDto getById(Long id) {
        Request req = repository.findById(id).orElseThrow(() ->
                new RequestNotFoundException(messageService.getMessage(NOT_FOUND_REQUEST, id))
        );
        return mapper.toResponseDto(req);
    }

    @Override
    public List<RequestResponseDto> getByAssociation(Long associationId) {
        existingAssociation(associationId);
        return repository.findByAssociationId(associationId).stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public List<RequestResponseDto> getByStatus(RequestStatus status) {
        return repository.findByStatus(status).stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public RequestResponseDto update(Long id, RequestRequestDto request) {

        existingAssociation(request.getAssociationId());

        Request existing = repository.findById(id).orElseThrow(() ->
                new RequestNotFoundException(messageService.getMessage(NOT_FOUND_REQUEST, id))
        );

        existing.setAssociationId(request.getAssociationId());
        existing.setTitle(request.getTitle());
        existing.setSummary(request.getSummary());
        existing.setDescription(request.getDescription());

        return mapper.toResponseDto(repository.save(existing));
    }

    @Override
    public RequestResponseDto updateStatus(Long id, RequestUpdateStatusDto request) {

        Request existing = repository.findById(id).orElseThrow(() ->
                new RequestNotFoundException(messageService.getMessage(NOT_FOUND_REQUEST, id))
        );

        existing.setStatus(request.getStatus());

        return mapper.toResponseDto(repository.save(existing));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void existingAssociation(Long associationId) {
        associationRepository.findById(associationId)
                .orElseThrow(() -> new AssociationNotFoundException(
                        messageService.getMessage("error.association.not.found", associationId)
                ));
    }
}