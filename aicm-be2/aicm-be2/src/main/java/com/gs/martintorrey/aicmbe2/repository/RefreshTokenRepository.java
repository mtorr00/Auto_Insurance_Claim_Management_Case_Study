package com.gs.martintorrey.aicmbe2.repository;

import com.gs.martintorrey.aicmbe2.model.RefreshToken;
import com.gs.martintorrey.aicmbe2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByUser(User user);
}
