package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

import java.util.List;

public interface IUserServicePort {
    void saveOwner(UserModel userModel);

    List<UserModel> getAllUsers();
}
