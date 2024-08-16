package com.rk.demo_jwt.repos.oracle;

import com.rk.demo_jwt.models.oracle.OracleTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestOracleRepo extends JpaRepository<OracleTestEntity,String> {
}
