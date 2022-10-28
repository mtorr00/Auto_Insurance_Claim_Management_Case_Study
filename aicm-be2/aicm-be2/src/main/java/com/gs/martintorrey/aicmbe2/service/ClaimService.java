package com.gs.martintorrey.aicmbe2.service;


import com.gs.martintorrey.aicmbe2.entity.ClaimEntity;

import java.util.List;

public interface ClaimService {
    //User
    List<ClaimEntity> getClaims(String username);
    ClaimEntity createClaim(ClaimEntity claim);

    //admin only
    List<ClaimEntity> getAllClaims();
    ClaimEntity saveClaim(ClaimEntity claim);
    void deleteClaim(ClaimEntity claim);
}
