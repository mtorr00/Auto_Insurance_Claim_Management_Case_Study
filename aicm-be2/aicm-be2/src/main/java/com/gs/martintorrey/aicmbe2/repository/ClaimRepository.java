package com.gs.martintorrey.aicmbe2.repository;

import com.gs.martintorrey.aicmbe2.entity.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimRepository extends JpaRepository<ClaimEntity,Long> {
}
