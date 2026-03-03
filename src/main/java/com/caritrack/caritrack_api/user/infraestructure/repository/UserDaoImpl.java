package com.caritrack.caritrack_api.user.infraestructure.repository;

import com.caritrack.caritrack_api.user.domain.User;
import com.caritrack.caritrack_api.user.domain.UserDao;
import com.caritrack.caritrack_api.user.infraestructure.mapper.UserRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserJpaRepository jpaRepository;
    private final UserRepositoryMapper mapper;

    @Override
    public User save(User user) {
        UserJpa entity = mapper.toJpa(user);
        UserJpa saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
