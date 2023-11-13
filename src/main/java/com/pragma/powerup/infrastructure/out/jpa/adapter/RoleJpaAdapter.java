package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository repository;
    private final IRoleEntityMapper entityMapper;
    @Override
    public RoleModel getRoleByName(String name) {
        Optional<RoleEntity> entity = repository.findOneByNameIgnoreCase(name);
        if (entity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return entityMapper.toModel(entity.get());
    }
}
