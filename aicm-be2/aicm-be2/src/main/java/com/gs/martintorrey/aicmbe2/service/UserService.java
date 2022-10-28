package com.gs.martintorrey.aicmbe2.service;

import com.gs.martintorrey.aicmbe2.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity validateAndGetUser(String username);
    Optional<UserEntity> getUser(String username);
    UserEntity saveUser(UserEntity userEntity);
}
