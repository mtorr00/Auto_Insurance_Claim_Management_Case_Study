package com.gs.martintorrey.aicmbe2.service;

import com.gs.martintorrey.aicmbe2.entity.UserEntity;
import com.gs.martintorrey.aicmbe2.exception.UserNotFoundException;
import com.gs.martintorrey.aicmbe2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserEntity validateAndGetUser(String username) {
        return getUser(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public Optional<UserEntity> getUser(String username) {
        UserEntity user;
        for (UserEntity a : new ArrayList<>(userRepository.findAll())){
            if (username.equals(a.getUsername())){
                user = a;
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }
}
