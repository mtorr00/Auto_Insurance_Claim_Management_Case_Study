package com.gs.martintorrey.aicmbe2.repository;

import com.gs.martintorrey.aicmbe2.entity.ClaimEntity;
import com.gs.martintorrey.aicmbe2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClaimRepository extends JpaRepository<ClaimEntity,Long> {
}
