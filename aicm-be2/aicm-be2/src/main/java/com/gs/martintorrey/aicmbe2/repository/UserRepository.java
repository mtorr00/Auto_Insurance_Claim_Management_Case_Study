package com.gs.martintorrey.aicmbe2.repository;

import com.gs.martintorrey.aicmbe2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
