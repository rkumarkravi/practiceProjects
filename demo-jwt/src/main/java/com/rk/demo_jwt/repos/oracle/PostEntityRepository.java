package com.rk.demo_jwt.repos.oracle;

import com.rk.demo_jwt.models.oracle.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostEntityRepository extends JpaRepository<PostEntity, Long> {
}