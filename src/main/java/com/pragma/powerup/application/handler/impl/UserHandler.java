package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserLoginDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.AuthTokenResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.infrastructure.security.utils.TokenUtils;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils jwtTokenUtil;

    @Override
    public AuthTokenResponseDto authenticateUser(UserLoginDto userLoginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return new AuthTokenResponseDto(token);
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        UserModel userModel = userRequestMapper.toUserModel(userRequestDto);
        String plainPassword = userModel.getPassword();
        userModel.setPassword(passwordEncoder.encode(plainPassword));
        userModel.setRoles(userModel.getRoles().stream().map(
                role -> roleServicePort.getRoleByName(role.getName())
        ).toList());
        return userResponseMapper.toResponse(userServicePort.saveOwner(userModel));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }
    @Override
    public UserResponseDto getUserById(Long userId) {
        return userResponseMapper.toResponse(userServicePort.getUserById(userId));
    }

    @Override
    public UserResponseDto getUserByIdentityNumber(Integer identityNumber) {
        return userResponseMapper.toResponse(userServicePort.getUserByIdentityNumber(identityNumber));
    }
    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userResponseMapper.toResponse(userServicePort.getUserByEmail(email));
    }
}