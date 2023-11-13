package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleUseCase implements IRoleServicePort {
    private final IRolePersistencePort rolePersistencePort;
    @Override
    public RoleModel getRoleByName(String name) {
        return rolePersistencePort.getRoleByName(name);
    }
}
