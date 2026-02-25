package com.caritrack.caritrack_api.role.infraestructure.repository;


import com.caritrack.caritrack_api.role.domain.Role;
import com.caritrack.caritrack_api.role.domain.RoleRepository;
import com.caritrack.caritrack_api.role.infraestructure.mapper.RoleRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository jpaRepository;
    private final RoleRepositoryMapper mapper;

    @Override
    public Role save(Role role) {
        RoleJpa roleJpa = mapper.toJpa(role);
        return mapper.toDomain(jpaRepository.save(roleJpa));
    }

    @Override
    public Optional<Role> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Role> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
