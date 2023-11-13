package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserLoginDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.AuthTokenResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {

    AuthTokenResponseDto authenticateUser(UserLoginDto userLoginDto);
    UserResponseDto saveUser(UserRequestDto userRequestDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long userId);
    UserResponseDto getUserByIdentityNumber(Integer identityNumber);

    UserResponseDto getUserByEmail(String email);
}