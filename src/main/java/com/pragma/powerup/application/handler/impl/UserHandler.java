package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto saveOwner(UserRequestDto userRequestDto) {
        UserModel userModel = userRequestMapper.toUserModel(userRequestDto);
        String plainPassword = userModel.getPassword();
        userModel.setPassword(passwordEncoder.encode(plainPassword));
        RoleModel userRole = new RoleModel(RoleModel.RoleEnum.OWNER);
        userModel.setRoles(List.of(userRole));
        return userResponseMapper.toResponse(userServicePort.saveOwner(userModel));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }
    @Override
    public UserResponseDto getUser(Long userId) {
        return userResponseMapper.toResponse(userServicePort.getUser(userId));
    }
}