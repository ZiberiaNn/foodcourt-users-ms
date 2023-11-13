package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;


    @Override
    public UserModel saveUser(UserModel userModel) {
        UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(userModel));
        return userEntityMapper.toModel(userEntity);
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> entityList = userRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toModelList(entityList);
    }

    @Override
    public UserModel getUserById(Long userId) {
        Optional<UserEntity> entity = userRepository.findById(userId);
        if (entity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toModel(entity.get());
    }

    @Override
    public UserModel getUserByIdentityNumber(Integer identityNumber) {
        Optional<UserEntity> entity = userRepository.findOneByIdentityDocument(identityNumber);
        if (entity.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toModel(entity.get());
    }

    @Override
    public UserModel getUserByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findOneByEmail(email);
        return entity.map(userEntityMapper::toModel).orElse(null);
    }
}