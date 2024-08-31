package com.rk.demo_jwt.repos.oracle;

import com.rk.demo_jwt.models.oracle.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}