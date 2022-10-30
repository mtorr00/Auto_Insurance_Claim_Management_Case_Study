package com.gs.martintorrey.aicmbe2.service;


import com.gs.martintorrey.aicmbe2.entity.ClaimEntity;

import java.util.List;

public interface ClaimService {
    //User
    List<ClaimEntity> getClaims(String username, String authType);
    ClaimEntity createClaim(String username, ClaimEntity claim, String authType);

    //admin only
    ClaimEntity saveClaim(String username, ClaimEntity claim, String authType);
    void deleteClaim(String username, ClaimEntity claim, String authType);
}
